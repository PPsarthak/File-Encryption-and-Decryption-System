import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SwingGUI implements ActionListener {
    JFrame f;
    JButton b;
    static JTextField l;
    JButton aes;
    JButton bf;
    JButton cast;
    JButton save;
    static JTextField fpath;
    JFileChooser saveFile;
    String secretKey = "File_Encryption_System";
    File fileO;
    File file1;
    JButton aesDecrypt;
    JButton bfDecrypt;
    JButton cascadeDecrypt;
    JFileChooser file;
    JTextField key;
    public SwingGUI(){

        f = new JFrame("File Encryption and Decryption System");
        b = new JButton("Select File");
        l = new JTextField("No file selected");
        aes = new JButton("Encrypt using AES Algorithm");
        bf = new JButton("Encrypt using Blowfish Algorithm");
        cast = new JButton("Encrypt using Cascading");
        fpath = new JTextField();
        key = new JTextField("Default secret key: " + secretKey);
        aesDecrypt = new JButton("Decrypt AES Encryption");
        bfDecrypt = new JButton("Decrypt Blowfish Encryption");
        cascadeDecrypt = new JButton("Decrypt Cascade Encryption");

        l.setBounds(105,20,220,30);
        l.setEditable(false);
        l.setBackground(Color.lightGray);

        b.setBounds(175,70,100,30);
        b.addActionListener(e -> {
            file = new JFileChooser("D:\\");
            int resp = file.showOpenDialog(null);
            if(resp == JFileChooser.APPROVE_OPTION) {
                fileO = new File(file.getSelectedFile().getAbsolutePath());
                fpath.setText("File Selected: " + fileO);
                l.setText("File Selected ");
            }
        });

        key.addActionListener(e->{
            secretKey = key.getText();
            key.setText("New Secret Key is: " + secretKey);
        });
        aes.setBounds(100,130,250,30);
        bf.setBounds(100,190,250,30);
        cast.setBounds(100,250,250,30);

        aes.addActionListener(e -> {
            saveFile = new JFileChooser("D:\\");
            try{
                int saveResp = saveFile.showSaveDialog(null);
                if(saveResp==JFileChooser.APPROVE_OPTION){
                    file1 = new File(saveFile.getSelectedFile().getAbsolutePath());
                    l.setText("AES Encryption successful");
                }
                FileAES.encrypt(fileO.getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
            }
            catch(Exception ne){
                l.setText("AES Encryption canceled");
            }
        });

        bf.addActionListener(e -> {
            saveFile = new JFileChooser("D:\\");
            try{
                int saveResp = saveFile.showSaveDialog(null);
                if(saveResp==JFileChooser.APPROVE_OPTION){
                    fpath.setText("File Saved Successfully");
                    l.setText("Blowfish Encryption successful");
                }
                FileBlowfish.encrypt(file.getSelectedFile().getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
            }
            catch (Exception ne){
                l.setText("Blowfish Encryption canceled");
            }

        });

        cast.addActionListener(e -> {
            saveFile = new JFileChooser("D:\\");
            try{
                int saveResp = saveFile.showSaveDialog(null);
                if(saveResp==JFileChooser.APPROVE_OPTION){
                    fpath.setText("File Saved Successfully");
                    l.setText("Cascade Encryption successful");
                }
                FileAES.encrypt(file.getSelectedFile().getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
                FileBlowfish.encrypt(saveFile.getSelectedFile().getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
            }
            catch (Exception ne){
                l.setText("Cascade Encryption canceled");
            }

        });

        aesDecrypt.setBounds(100,310,250,30);
        aesDecrypt.addActionListener(e -> {
            saveFile = new JFileChooser("D:\\");
            try{
                int saveResp = saveFile.showSaveDialog(null);
                if(saveResp==JFileChooser.APPROVE_OPTION){
                    file1 = new File(saveFile.getSelectedFile().getAbsolutePath());
                }
                FileAES.decrypt(fileO.getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
                l.setText("AES Decryption successful");
            }
            catch (Exception ne){
                l.setText("AES Decryption canceled");
            }
        });

        bfDecrypt.setBounds(100,370,250,30);
        bfDecrypt.addActionListener(e -> {
            saveFile = new JFileChooser("D:\\");
            try{
                int saveResp = saveFile.showSaveDialog(null);
                if(saveResp==JFileChooser.APPROVE_OPTION){
                    fpath.setText("File Saved Successfully");
                    l.setText("Blowfish Decryption successful");
                }
                    FileBlowfish.decrypt(file.getSelectedFile().getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
            }
            catch(Exception ne){
                l.setText("Blowfish Decryption canceled");
            }

        });

        cascadeDecrypt.setBounds(100,430,250,30);
        cascadeDecrypt.addActionListener(e -> {
            saveFile = new JFileChooser("D:\\");
            try {
                int saveResp = saveFile.showSaveDialog(null);
                if(saveResp==JFileChooser.APPROVE_OPTION){
                    fpath.setText("File Saved Successfully");
                    l.setText("Cascade Decryption successful");
                }
                    FileBlowfish.decrypt(file.getSelectedFile().getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
                    FileAES.decrypt(saveFile.getSelectedFile().getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
            }
            catch (Exception ne){
                l.setText("Cascade Decryption canceled");
            }

        });

        key.setBounds(50,540,250,30);

        fpath.setBounds(50,490,400,30);
        fpath.setEditable(false);
        fpath.setText("No files selected");
        fpath.setBackground(Color.lightGray);
//        f.getContentPane().setBackground(new Color(33,37,43));
        f.setIconImage(Toolkit.getDefaultToolkit().getImage("Encryption.png"));
        f.add(b);
        f.add(aes);
        f.add(bf);
        f.add(cast);
        f.add(l);
        f.add(fpath);
        f.add(aesDecrypt);
        f.add(bfDecrypt);
        f.add(cascadeDecrypt);
        f.add(key);
        f.setSize(480,650);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
    }

    public static void main(String[] args) {
        new SwingGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        }
    }

