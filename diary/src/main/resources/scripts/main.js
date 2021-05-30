const campo_quadro = document.querySelector("#quadros");

var todosEX = window.localStorage.getItem("todos");
var todos = JSON.parse(todosEX);

class Item {
    constructor(in_data, in_titulo, in_texto){
        var quadro = document.createElement('div');
    
        var fechar = document.createElement('button');
        fechar.innerHTML = 'Editar';
        fechar.style.fontSize = '20px'
        fechar.style.background = 'none';
        fechar.style.border = 'none';
        fechar.style.color = '#ffffff';
        fechar.type = "button";

        var data = document.createElement('p');
        data.style.marginTop = "5px";
        data.style.fontSize = '12px';
        data.setAttribute("name", "data");
        if(in_data == "") {
            var date = new Date();
            let hora = date.getHours();
            let minuto = date.getMinutes();
            let segundos = date.getSeconds();
            let dia = date.getDate();
            let mes = date.getMonth() + 1;
            let ano = date.getFullYear();
            var data_atual = "["+hora+":"+minuto+":"+segundos+"]"+"("+dia+'/'+mes+'/'+ano+")";
            data.innerHTML = data_atual;
        } else {
            var data_atual = in_data;
            data.innerHTML = in_data;
        }


        var titulo = document.createElement('input');
        titulo.setAttribute("name", "titulo");
        titulo.style.border = 'none';
        titulo.disabled = 'true';
        titulo.style.color = '#ffffff';
        titulo.placeholder = 'Sem titulo';
        titulo.style.height = '10%';
        if(in_titulo != "") {
            titulo.value = in_titulo;
        }
        
        var texto = document.createElement('textarea');
        texto.setAttribute("name", "texto");
        texto.style.background = 'none';
        texto.style.color = '#ffffff';
        texto.style.fontFamily = "'Roboto Mono', monospace";
        texto.style.fontSize = '20px';
        texto.style.resize = 'none';
        texto.placeholder = "Nada ainda...";
        texto.style.border = 'none';
        texto.disabled = true;
        texto.style.height = '60%';
        texto.style.lineHeight = '34px';
        if(in_texto != "") {
           texto.value = in_texto
        }
        
        var adicionar = document.createElement('button');
        adicionar.innerHTML = 'Adicionar';
        adicionar.style.backgroundColor = '#6fff8e';
        adicionar.style.display = 'none';
        adicionar.type = "button"

        var remover = document.createElement('button');
        remover.innerHTML = 'Remover';
        remover.style.backgroundColor = '#ff594d';
        remover.type = "button";
        
        var visualizar = document.createElement('button')
        visualizar.innerHTML = 'Abrir card';
        visualizar.style.display = 'none';
        visualizar.type = "button"
        if(in_texto != "") {
            visualizar.style.display = 'block';
        }
        
        var botoes = document.createElement('div');

        // botoes.append(adicionar);
        botoes.append(visualizar)
        botoes.append(remover);
        
        quadro.append(fechar);
        quadro.append(data);
        quadro.append(titulo);
        quadro.append(texto);
        quadro.append(botoes);
        
        
        remover.addEventListener('click', (e) => {
            let index = 0;
            let i = 0;
            while(i < todos.length) {
                if(Object(todos[i])["data"] == data_atual){
                    index = i;
                    break;
                }
                i++;
            }

            window.localStorage.removeItem(index);
            todos.splice(index, 1); //remove item
            window.localStorage.setItem("todos", JSON.stringify(todos));
            campo_quadro.removeChild(quadro);
        })

        fechar.addEventListener('click', (e) => {
            if(quadro.style.width != '80vw') {
                fechar.innerHTML = 'Fechar';
                
                quadro.style.width = '80vw';
                quadro.style.height = '80vh';
                
                adicionar.style.display = 'block';
                
                titulo.style.border = '2px solid #ffffff';
                titulo.style.borderRadius = '5px';
                titulo.disabled = false;
                
                texto.style.border = '2px solid #ffffff';
                texto.style.borderRadius = '5px';
                texto.disabled = false;              
            } else {
                fechar.innerHTML = 'Editar';

                quadro.style.width = '300px';
                quadro.style.height = '500px';
                
                adicionar.style.display = 'none';
                
                titulo.style.border = 'none';
                titulo.disabled = true;
                titulo.style.height = '10%';
                
                texto.style.border = 'none';
                texto.disabled = true;
                texto.style.height = '65%';
                texto.style.marginTop = "10px";

                let index = -1;
                let i = 0;
                while(i < todos.length) {
                    if(Object(todos[i])["data"] == data_atual){
                        index = i;
                        break;
                    }
                    i++;
                }

                if(texto.value != "") {
                    if(index == -1) {
                        todos.push({data:data_atual, titulo:titulo.value, texto:texto.value});
                        window.localStorage.setItem("todos", JSON.stringify(todos));
                    } else {
                        todos[index] = {data:data_atual, titulo:titulo.value, texto:texto.value};
                        window.localStorage.setItem("todos", JSON.stringify(todos));
                    }
                }
            } 
        
            if(texto.value != "") {
                visualizar.style.display = 'block';
            }
        })
        
        visualizar.addEventListener('click', (e) => {
            fechar.innerHTML = 'Fechar';
                
            quadro.style.width = '80vw';
            quadro.style.height = '80vh';
            
            titulo.disabled = true;
            
            texto.disabled = true;
        
            visualizar.style.display = 'none';
        })

        campo_quadro.insertBefore(quadro, campo_quadro.firstChild);
    }
}

for (let i = 0; i < todos.length; i++) {
    new Item(todos[i]["data"], todos[i]["titulo"], todos[i]["texto"]);
}

add = document.getElementById('add');
//add = document.getElementById('add');
add.addEventListener('click', (e) => {
    var novo_quadro = new Item("", "", "");
})

save = document.getElementById('save');
save.addEventListener('click', (e)=>{
    // for(var card in todos) {
        alert("Elmentos salvos");
        var dados = (JSON.stringify(todos)).toString();

        $.ajax({
            method: "GET",
            MimeType: "text/plain",
            
            dataType: 'text/plain',
            url: "http://127.0.0.1:6580/agenda",
            data: dados
        })
        
        // httpRequest.open('GET', "http://127.0.0.1:6580/agenda" );
        // httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        // httpRequest.send(JSON.parse(card));
    // }
})