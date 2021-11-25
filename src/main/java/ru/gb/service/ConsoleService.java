package ru.gb.service;

import ru.gb.model.Cart;
import ru.gb.repository.ProductRepository;
import ru.gb.utils.CommandEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleService {
    private final ProductRepository productRepository;
    private final Cart cart;

    public ConsoleService() {
        this.productRepository = new ProductRepository();
        this.cart = new Cart(productRepository);
    }

    private void printCommand() {
        StringBuilder builder = new StringBuilder();
        builder.append(CommandEnum.SHOWP).append(" ").append("Available list products.").append("\n");
        builder.append(CommandEnum.SHOWC).append(" ").append("See products in cart.").append("\n");
        builder.append(CommandEnum.ADD).append(" 0").append(" ").append("Added product in cart by id.").append("\n");
        builder.append(CommandEnum.REMOVE).append(" 0").append(" ").append("Remove product in cart by id.").append("\n");
        builder.append(CommandEnum.CLEAR).append(" ").append("Remove all products in cart.").append("\n");
        System.out.println(builder);
    }

    public void run() {
        printCommand();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String command = reader.readLine();
                if (command.matches("^[a-zA-Z]+$|^[a-zA-Z]+ \\d{1,3}")) {
                    String commandType = command.split(" ")[0];
                    int id = 0;
                    try {
                        id = Integer.parseInt(command.replaceAll("[a-zA-Z]|\\s", ""));
                    } catch (NumberFormatException ignore) {
                    }
                    execCommand(commandType, id);
                } else {
                    System.err.println("No such command please try again!");
                    printCommand();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void execCommand(String commandType, int id) {
        switch (CommandEnum.valueOf(commandType.toUpperCase())) {
            case SHOWP: {
                productRepository.printProducts();
                break;
            }
            case SHOWC: {
                cart.printCart();
                break;
            }
            case ADD: {
                cart.addProduct(id);
                System.out.printf("Product %s added!\n", id);
                cart.printCart();
                break;
            }
            case REMOVE: {
                cart.deleteProduct(id);
                System.out.printf("Product %s deleted!\n", id);
                cart.printCart();
                break;
            }
            case CLEAR: {
                cart.removeAllProductInCart();
                System.out.println("All products removing!");
                break;
            }
            default: {
                System.err.println("No such command please try again!");
                printCommand();
                break;
            }
        }
    }
}
