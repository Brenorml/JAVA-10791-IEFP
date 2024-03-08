package menu;

import java.util.Scanner;
import java.io.*;
import classes.Item;
import classes.ItemFileHandler;;

public class Main {
	private static final int MAX_ITEMS = 20;
    private static final Scanner scanner = new Scanner(System.in);
    
	public static void main(String[] args) {
		int itemCount = 0;
        String fileName = "";
        Item[] itemsArray = new Item[MAX_ITEMS];

        int option;
        boolean fileOpened = false;

        do {
            displayMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha após a entrada do número

            switch (option) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:
                    if (fileOpened) {
                        itemCount += insertItem(itemsArray, itemCount);
                    } else {
                        System.out.println("Nenhum arquivo foi aberto.");
                    }
                    break;
                case 2:
                    if (fileOpened) {
                        printItemList(itemsArray, itemCount);
                    } else {
                        System.out.println("Nenhum arquivo foi aberto.");
                    }
                    break;
                case 3:
                    if (fileOpened) {
                        deleteItem(itemsArray, itemCount);
                    } else {
                        System.out.println("Nenhum arquivo foi aberto.");
                    }
                    break;
                case 4:
                    if (fileOpened) {
                        editItem(itemsArray, itemCount);
                    } else {
                        System.out.println("Nenhum arquivo foi aberto.");
                    }
                    break;
                case 5:
                    if (fileOpened) {
                        try {
                            ItemFileHandler.writeItemList(itemsArray, fileName);
                            System.out.println("Arquivo guardado com sucesso.");
                        } catch (IOException e) {
                            System.err.println("Erro ao escrever no arquivo.");
                        }
                    } else {
                        System.out.println("Nenhum arquivo foi aberto.");
                    }
                    break;
                case 6:
                	System.out.print("Digite o nome do arquivo: ");
                    fileName = scanner.nextLine().trim() + ".txt"; // Adicionar a extensão .txt ao nome do arquivo
                    System.out.println("Tentando abrir o arquivo: " + fileName);
                	itemsArray = openFile(fileName); // Atualizar o array de itens
                    fileOpened = true;
                    break;
                default:
                    System.out.println("Opcao invalida!!!");
            }
        } while (option != 0);

        scanner.close();
	}
	// Exibir menu
    private static void displayMenu() {
        System.out.println("\n\n\t1 - Inserir\n\t2 - Listar\n\t3 - Apagar\n\t4 - Editar\n\t5 - Gravar\n\t6 - Ler\n\t0 - Sair\n\nEscolha uma opcao: ");
    }

    // Inserir um novo item na lista
    private static int insertItem(Item[] itemsArray, int itemCount) {
        System.out.print("Digite o nome do item: ");
        String description = scanner.nextLine().trim();

        System.out.print("Digite a quantidade: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a entrada do número

        System.out.print("Digite o preco: ");
        float price = scanner.nextFloat();
        scanner.nextLine(); // Consumir a nova linha após a entrada do número

        itemsArray[itemCount] = new Item(itemCount + 1, description, quantity, price);
        System.out.println("Item inserido com sucesso!");
        return 1;
    }

    // Imprimir a lista de itens
    private static void printItemList(Item[] itemsArray, int itemCount) {
        //Imprimir cabeçalho
        System.out.println("Item      |Descricao                |Quantidade          |Preco     |Total     ");
        System.out.println("--------------------------------------------------------------------------------");

        // Imprimir cada item
        for (int i = 0; i < itemCount; ++i) {
            Item item = itemsArray[i];
            if (item != null) {
                System.out.printf("%-10d|%-25s|%-20d|%-10.2f|%-10.2f%n", 
                    item.getId(), item.getDescription(), item.getQuantity(), item.getPrice(), item.getTotal());
                System.out.println("--------------------------------------------------------------------------------");
            }
        }
    }

    // Apagar um item da lista
    private static void deleteItem(Item[] itemsArray, int itemCount) {
        System.out.print("Digite o ID do item a ser apagado: ");
        int idToDelete = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a entrada do número

        boolean found = false;
        for (int i = 0; i < itemCount; i++) {
            if (itemsArray[i].getId() == idToDelete) {
                itemsArray[i] = null;
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Item apagado com sucesso!");
        } else {
            System.out.println("Nenhum item encontrado com o ID fornecido.");
        }
    }

    // Editar um item da lista
    private static void editItem(Item[] itemsArray, int itemCount) {
        System.out.print("Digite o ID do item a ser editado: ");
        int idToEdit = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após a entrada do número

        for (int i = 0; i < itemCount; i++) {
            Item item = itemsArray[i];
            if (item != null && item.getId() == idToEdit) {
                System.out.print("Digite o novo nome do item: ");
                item.setDescription(scanner.nextLine());

                System.out.print("Digite a nova quantidade: ");
                int newQuantity = Integer.parseInt(scanner.nextLine());
                item.setQuantity(newQuantity);

                System.out.print("Digite o novo preco: ");
                double newPrice = Double.parseDouble(scanner.nextLine());
                item.setPrice((float) newPrice);

                // Calcular e atualizar o total após a edição
                float newTotal = (float) (newPrice * newQuantity);
                //item.setTotal(newTotal); // Não é necessário atualizar o total

                System.out.println("Item editado com sucesso!");
                return; // Sair do método após editar o item
            }
        }
        System.out.println("ID inválido ou item não encontrado.");
    }



    // Abrir um arquivo de itens
    private static Item[] openFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            // Se o arquivo existir, abra-o
            System.out.println("Arquivo encontrado. Abrindo...");
            try {
                return ItemFileHandler.createItemList(fileName);
            } catch (FileNotFoundException e) {
                System.err.println("Erro ao abrir o arquivo: Arquivo não encontrado.");
                return new Item[MAX_ITEMS]; // retornar um novo array de itens
            }
        } else {
            // Se o arquivo não existir, crie um novo
            try {
                System.out.println("Arquivo não encontrado. Criando novo arquivo...");
                file.createNewFile();
                System.out.println("Novo arquivo criado com sucesso.");
                return new Item[MAX_ITEMS]; // retornar um novo array de itens
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo: " + e.getMessage());
                return new Item[MAX_ITEMS]; // retornar um novo array de itens
            }
        }
    }

}
