/**
 * @author Nadia Fasani
 * @version 01.05.2021
*/

public class Main{
    public static void main(String[] args) {
        Gui gui = new Gui("Caesar Cipher");

        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                gui.setVisible(true);
            }
        });

    }

}
