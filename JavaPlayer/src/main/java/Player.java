import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Player {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);

        File file = new File("sugar.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        String response = scanner.next();

        while (!response.equals("Q")){
            System.out.println("P = play, S=Stop, R=rest, Q=Quit");
            System.out.println("\nEnter, your choice:");

            response = scanner.next();
            response = response.toUpperCase();

            switch (response){
                case ("P"): clip.start();
                break;
                default:
                    System.out.println("\nNot a valid response");
            }
        }

        clip.start();

    }

}
