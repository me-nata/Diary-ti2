//package java.com.ti2cc;

/*
 * Author: Bryan Jonathan Melo De Oliveira
 */

package com.ti2cc;

//import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
	//Scanner sc = new Scanner(System.in);	
		DAO dao = new DAO();
		int x = 0;
		int id = 0;
		int idade = 0;
		boolean p = false;
		String str1 = "";
		String str2 = "";
		String str3 = "";
		
		dao.conectar();
       
	
		
		System.out.println("O que você deseja fazer ?");
		System.out.println("1- Listar");
		System.out.println("2- Inserir");
		System.out.println("3- Excluir");
		System.out.println("4- Atualizar");
		System.out.println("5- Sair");
	   
		x = MyIO.readInt();
		
		while(x != 5) {
		if(x == 1)
		{
			//Mostrar carros
			Usuario[] usuarios = dao.getUsuarios();
			System.out.println("==== Mostrar usuarios === ");		
			for(int i = 0; i < usuarios.length; i++) {
				System.out.println(usuarios[i].toString());
			}
		}
		
		if(x == 2)
		{
			//Inserir um elemento na tabela
			System.out.println("Qual o id do usuario?");
			id = MyIO.readInt();
			str1 = MyIO.readString("Qual o nome do usuario? ");
			str2 = MyIO.readString("Qual o email do usuario? ");
			str3 = MyIO.readString("Qual a senha do usuario? ");
			System.out.println("Qual a idade do usuario?");
			idade = MyIO.readInt();
			System.out.println("Este usuario é premium ?");
			p = MyIO.readBoolean();
			Usuario usuario = new Usuario(id, str1, str2, str3, idade, p);
			if(dao.inserirUsuario(usuario) == true) {
				System.out.println("Inserção com sucesso -> " + usuario.toString());
			 
			}
			
		}
		
		if(x == 3)
		{
			System.out.println("Qual o codigo do usuario que deseja excluir ?");
			id = MyIO.readInt();
			dao.excluirUsuario(id);
		}
		
		if(x == 4)
		{
			//Atualizar carro
            Usuario usuario = new Usuario();
            System.out.println("Qual o id do usuario?");
            id = MyIO.readInt();
            str1 = MyIO.readString("Qual o nome do usuario? ");
			str2 = MyIO.readString("Qual o email do usuario? ");
			str3 = MyIO.readString("Qual a senha do usuario? ");
			System.out.println("Qual a idade do usuario?");
            idade = MyIO.readInt();
            System.out.println("Este usuario se tornou premium?");
            p = MyIO.readBoolean();
            usuario.setId(id);
            usuario.setNome(str1);
            usuario.setEmail(str2);
            usuario.setSenha(str3);
            usuario.setIdade(idade);
            usuario.setPremium(p);
			dao.atualizarUsuario(usuario);
		}
	  
      
		System.out.println("O que você deseja fazer ?");
		System.out.println("1- Listar");
		System.out.println("2- Inserir");
		System.out.println("3- Excluir");
		System.out.println("4- Atualizar");
		System.out.println("5- Sair");
		
		x = MyIO.readInt();
	  }
		dao.close();
		}
}
