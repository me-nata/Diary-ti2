
esqueci_senha = document.getElementById("esqueci-senha");
esqueci_senha.style.display = "inherit";

fechar = document.getElementById("fechar");

container = document.getElementById("container-esqueci-senha");
container.style.display = "none";

esqueci_senha.addEventListener("click", function(e){
    container.style.display = "inherit";
})
fechar.addEventListener("click", function(e){
    container.style.display = "none";
})