package classes;

import java.util.Scanner;
import java.io.*;

public class ItemFileHandler {
	// Verificar se um arquivo existe
    public static boolean verifyFile(String fileName) {
        File file = new File(fileName);
        return file.exists() && !file.isDirectory();
    }

    // Criar uma lista de itens a partir de um arquivo
    public static Item[] createItemList(String fileName) throws FileNotFoundException {
        int lineCount = 0;
        Item[] newItemsArray = new Item[20];
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        // Ignorar a primeira linha (cabeçalho)
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        // Ler e processar as linhas do arquivo
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                String[] tokens = line.split("\\|");
                if (tokens.length == 4) {
                    int id = Integer.parseInt(tokens[0].trim());
                    String description = tokens[1].trim();
                    int quantity = Integer.parseInt(tokens[2].trim());
                    float price = Float.parseFloat(tokens[3].trim());
                    newItemsArray[lineCount] = new Item(id, description, quantity, price);
                    lineCount++;
                }
            }
        }
        scanner.close();
        return newItemsArray;
    }
    
    

    // Escrever a lista de itens em um arquivo
 // Escrever a lista de itens em um arquivo
    public static void writeItemList(Item[] itemsArray, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        // Escrever cabeçalho
        printWriter.printf("%-10s|%-25s|%-20s|%-10s|%-10s%n", "Item", "Descricao", "Quantidade", "Preco", "Total");

        // Escrever cada item no arquivo
        for (Item item : itemsArray) {
            if (item != null) {
                printWriter.printf("%-10d|%-25s|%-20d|%-10.2f|%-10.2f%n", item.getId(), item.getDescription(), item.getQuantity(), item.getPrice(), item.getTotal());
            }
        }

        printWriter.close();
    }

}
