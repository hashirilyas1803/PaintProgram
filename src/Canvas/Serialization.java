package Canvas;

import Default.Board;
import Default.LayersToolBar;

import java.io.*;
import java.util.LinkedList;

public class Serialization implements Serializable {
String filepath = "";
    public Serialization() {
    }

    public void writing(Board board) {


            try {

                FileOutputStream fileOut = new FileOutputStream(filepath);
                ObjectOutputStream writer = new ObjectOutputStream(fileOut);
//                while (stack.top != null){
//                    Shape data = stack.pop();
//                    writer.writeObject(data);
//                    writer.write('\n');
//                }
                writer.writeObject(board.layer);


                writer.close();
                fileOut.close();
            } catch (IOException ex) {
                System.out.println("File not found!");
                ex.printStackTrace();
            }

    }

    public void reading(Board board){
        try {

            FileInputStream fileIn = new FileInputStream(filepath );
            ObjectInputStream in = new ObjectInputStream(fileIn);
//            while (stack.top != null) {
//                Shape data = stack.pop();
//                writer.writeObject(data);
//            }
//            stack = (Stack) in.readObject();

           LinkedList<Stack<Shape>> temp = (LinkedList<Stack<Shape>>) in.readObject();


            for (int i = 0; i < temp.size(); i++) {
                board.layers.addlayer();
            }
            for (int i = 0; i < temp.size(); i++) {
                board.layer.set(i,temp.get(i));
            }
            board.layers.removelayer();
            in.close();
            fileIn.close();
            File folder = new File("src/Files");
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                }
            }
//            while (!stack.isEmpty()) {
//                shape = stack.pop();
//                System.out.println(shape);
//            }
        } catch (IOException ex) {
            System.out.println("File not found!");
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath + ".ser";
    }
}