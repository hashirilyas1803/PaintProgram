package Canvas;

import Default.Board;
import Default.LayersToolBar;

import java.io.*;
import java.util.LinkedList;

public class Serialization implements Serializable {
String filepath = "";
    File [] list;
    public Serialization() {
        File folder = new File("src/Files");
        list = folder.listFiles();
    }

    public void writing(Board board) {


            try {

                FileOutputStream fileOut = new FileOutputStream("src/Files/" + filepath);
                ObjectOutputStream writer = new ObjectOutputStream(fileOut);
//                while (stack.top != null){
//                    Shape data = stack.pop();
//                    writer.writeObject(data);
//                    writer.write('\n');
//                }
                writer.writeObject(board.layer);
                //writer.writeObject(board.redo);


                writer.close();
                fileOut.close();
            } catch (IOException ex) {
                System.out.println("File not found!");
                ex.printStackTrace();
            }

    }

    public void reading(Board board){
        try {

            FileInputStream fileIn = new FileInputStream("src/Files/" +filepath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
//            while (stack.top != null) {
//                Shape data = stack.pop();
//                writer.writeObject(data);
//            }
//            stack = (Stack) in.readObject();

           LinkedList<Stack<Shape>> temp = (LinkedList<Stack<Shape>>) in.readObject();
           //LinkedList<Queue<Shape>> temp1 = (LinkedList<Queue<Shape>>) in.readObject();


            for (int i = 0; i < temp.size(); i++) {
                board.layers.addlayer();
            }
            for (int i = 0; i < temp.size(); i++) {
                board.layer.set(i,temp.get(i));
            }
//            for (int i = 0; i < temp1.size(); i++) {
//                board.redo.set(i,temp1.get(i));
//            }

            board.layers.removelayer();
            in.close();
            fileIn.close();



//            while (!stack.isEmpty()) {
//                shape = stack.pop();
//                System.out.println(shape);
//            }
        }
        catch (EOFException exe){
            System.out.println("File empty");
            return;
        }
        catch (IOException ex) {
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
        this.filepath = filepath;
    }

    public File[] getList() {
        return list;
    }
}