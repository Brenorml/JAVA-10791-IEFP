package jogos_advinhacao;

import java.util.Scanner;

public class JogoAdvinhacao {
	private int numeroSecreto;
	private int palpite;
	private int tentativas;


	private void gerarNumeroSecreto() {
		numeroSecreto = (int)(Math.random() * 100) + 1;
	}
	
	private void lerPalpite() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Digite seu palpite: ");
		palpite = scanner.nextInt();
		scanner.close();
	}
	
	private void advinhar() {
		tentativas = 0;
		
		while(palpite != numeroSecreto) {
			tentativas++;
			
			if(palpite < numeroSecreto) {
				System.out.println("Seu palpite é menor que o número secreto!");
			}
			else {
				System.out.println("Seu palpite é maior que o número secreto!");
			}
			lerPalpite();
		}
		System.out.println("Parabéns! Você advinhou o número secreto em " + tentativas + " tentativas!");
	}
	
	public void mostrarResultado() {
		System.out.println("O número secreto era: " + numeroSecreto);
		System.out.println("Você utilizou " + tentativas + " tentativas!");
	}

	public static void main(String[] args) {
		JogoAdvinhacao jogo = new JogoAdvinhacao();

		while(true) {
			jogo.gerarNumeroSecreto();
			jogo.lerPalpite();
			jogo.advinhar();
			jogo.mostrarResultado();
					
			System.out.println("Deseja jogar novamente? (s/n): ");
			Scanner scanner = new Scanner(System.in);
			String resposta = scanner.nextLine();
			scanner.close();
			
			if(!resposta.equalsIgnoreCase("s")) {				
				break;
			}
		}	

	}

}
