package chaplinskiy.crud;

import chaplinskiy.crud.view.InputConsole;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Создать город??");
        InputConsole inputConsole = new InputConsole();
        inputConsole.readMessage();
    }


}
