import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Date;

public class Gui implements ActionListener {

    private JFrame jFrame;
    private JPanel jpanel;
    private JPanel jpanel2;
    private JPanel jpanel3;
    private JButton jButton;
    private JButton jButton1;
    private JButton jButton2;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JCheckBox jCheckBox;
    private String status="stopped";
    private String adress="127.0.0.1";
    private String port="9090";
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JTextField jTextField;
    private String maintenance="C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\maintenance\\";
    private String root="C:\\Users\\Daniel\\Desktop\\Huple_Razvan\\Week_01\\client\\";
    private JLabel rootLabel;
    private JLabel maintenanceLabel;
    private Image image1;
    private Image image2;
    private WebServer webServer;

    public void createGui(){
        int p = Integer.parseInt(port);
        webServer = new WebServer(p,root,maintenance,State.Stopped);
        createPanel1();
        createPanel2();
        createPanel3();
        createFrame();
    }

    public void createFrame() {
        jFrame = new JFrame();
        jFrame.setTitle("");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);
        jFrame.getContentPane().setBackground(new Color(223,224, 142));
        jFrame.setResizable(false);
        jFrame.setTitle("VVS web server - [" + status +"]");

        jFrame.add(jpanel);
        jFrame.add(jpanel2);
        jFrame.add(jpanel3);
    }

    public void createPanel1() {
        JLabel jLabel1 = new JLabel("Server status:");
        JLabel jLabel2 = new JLabel("Server adress:");
        JLabel jLabel3 = new JLabel("Server listening port:");
        jLabel7 = new JLabel();
        jLabel7.setText("not running");
        jLabel8 = new JLabel();
        jLabel8.setText("not running");
        jLabel9 = new JLabel();
        jLabel9.setText("not running");

        jLabel1.setSize(120,15);
        jLabel2.setSize(120,15);
        jLabel3.setSize(120,15);
        jLabel7.setSize(120,15);
        jLabel8.setSize(120,15);
        jLabel9.setSize(120,15);

        jLabel1.setLocation(10,40);
        jLabel2.setLocation(10,120);
        jLabel3.setLocation(10,200);
        jLabel7.setLocation(130,40);
        jLabel8.setLocation(130,120);
        jLabel9.setLocation(130,200);

        TitledBorder titledBorder = BorderFactory.createTitledBorder("WebServer info");

        jpanel = new JPanel();
        jpanel.setBackground(Color.orange);
        jpanel.setBounds(0,0,250,250);
        jpanel.setLayout(null);
        jpanel.setBorder(titledBorder);
        jpanel.add(jLabel1);
        jpanel.add(jLabel2);
        jpanel.add(jLabel3);
        jpanel.add(jLabel7);
        jpanel.add(jLabel8);
        jpanel.add(jLabel9);
    }

    public void createPanel2() {
        jButton = new JButton("Start");
        jButton.addActionListener(this);
        jCheckBox = new JCheckBox("Switch to maintenance mode");
        jCheckBox.addActionListener(this);
        jCheckBox.setEnabled(false);
        TitledBorder titledBorder2 = BorderFactory.createTitledBorder("WebServer control");
        jpanel2 = new JPanel();
        jpanel2.setBackground(Color.red);
        jpanel2.setBounds(250,0,250,250);
        jpanel2.setBorder(titledBorder2);
        jpanel2.add(jButton);
        jpanel2.add(jCheckBox);
    }

    public void createPanel3() {
        JLabel jLabel4 = new JLabel("Server listening on port:");
        JLabel jLabel5 = new JLabel("Web root directory:");
        JLabel jLabel6 = new JLabel("Maintenance directory:");
        jTextField = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jButton1 = new JButton("...");
        jButton1.addActionListener(this);
        jButton2 = new JButton("...");
        jButton2.addActionListener(this);
        URL accept = null;
        URL decline = null;
        try {
            accept = new URL("https://icons.iconarchive.com/icons/custom-icon-design/pretty-office-8/16/Accept-icon.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            decline = new URL("https://icons.iconarchive.com/icons/ampeross/qetto-2/16/no-icon.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        image1 = null;
        image2 = null;
        try {
            image1 = ImageIO.read(accept);
            image2 = ImageIO.read(decline);
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootLabel = new JLabel(new ImageIcon(image1));
        maintenanceLabel = new JLabel(new ImageIcon(image1));

        jLabel4.setSize(140,15);
        jLabel5.setSize(140,15);
        jLabel6.setSize(140,15);
        jTextField.setSize(40,20);
        jTextField2.setSize(200,20);
        jTextField3.setSize(200,20);
        jButton1.setSize(30,20);
        jButton2.setSize(30,20);
        rootLabel.setSize(30,20);
        maintenanceLabel.setSize(30,20);

        jLabel4.setLocation(10,40);
        jLabel5.setLocation(10,100);
        jLabel6.setLocation(10,160);
        jTextField.setLocation(180,40);
        jTextField2.setLocation(180,100);
        jTextField3.setLocation(180,160);
        jTextField.setText(port);
        jTextField2.setText(root);
        jTextField3.setText(maintenance);
        jButton1.setLocation(400,100);
        jButton2.setLocation(400,160);
        rootLabel.setLocation(450,100);
        maintenanceLabel.setLocation(450,160);

        TitledBorder titledBorder3 = BorderFactory.createTitledBorder("WebServer configuration");
        jpanel3 = new JPanel();
        jpanel3.setBackground(Color.yellow);
        jpanel3.setBounds(0,250,500,500);
        jpanel3.setBorder(titledBorder3);
        jpanel3.setLayout(null);
        jpanel3.add(jLabel4);
        jpanel3.add(jLabel5);
        jpanel3.add(jLabel6);
        jpanel3.add(jTextField);
        jpanel3.add(jTextField2);
        jpanel3.add(jTextField3);
        jpanel3.add(jButton1);
        jpanel3.add(jButton2);
        jpanel3.add(rootLabel);
        jpanel3.add(maintenanceLabel);
    }

    public static void main(String []args)
    {
        Gui g = new Gui();
        g.createGui();
        while(true) {
            try {
                WebServer.pornire(g.webServer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==jButton)
        {
            if(jButton.getText().equals("Start"))
            {
                port = jTextField.getText();
                root = jTextField2.getText();
                maintenance = jTextField3.getText();
                jLabel7.setText("running..");
                jLabel8.setText(adress);
                jLabel9.setText(port);
                jButton.setText("Stop");
                status="running";
                jCheckBox.setEnabled(true);
                jTextField.setEnabled(false);
                jTextField2.setEnabled(false);
            }
            else {
                maintenance = jTextField3.getText();
                jButton.setText("Start");
                jLabel7.setText("not running");
                jLabel8.setText("not running");
                jLabel9.setText("not running");
                jCheckBox.setEnabled(false);
                jTextField.setEnabled(true);
                jTextField2.setEnabled(true);
                jTextField3.setEnabled(true);
                jCheckBox.setSelected(false);
                status="stopped";
            }
            jFrame.setTitle("VVS web server - [" + status + "]");
        }
        else if(actionEvent.getSource()==jButton1)
        {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int response = jFileChooser.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION)
            {
                root = jFileChooser.getSelectedFile().getAbsolutePath();
                System.out.println(root);
                jTextField2.setText(root);
                setValidityRootDirectory();
            }
        }
        else if(actionEvent.getSource()==jButton2)
        {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int response = jFileChooser.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION)
            {
                maintenance = jFileChooser.getSelectedFile().getAbsolutePath();
                System.out.println(maintenance);
                jTextField3.setText(maintenance);
                setValidityMaintenanceDirectory();
            }
        }
        else if (actionEvent.getSource()==jCheckBox)
        {
            if(jCheckBox.isSelected())
            {
                maintenance = jTextField3.getText();
                status="maintenance";
                jLabel7.setText("maintenance");
                jTextField2.setEnabled(true);
                jTextField3.setEnabled(false);
            }
            else{
                root = jTextField2.getText();
                status="running";
                jLabel7.setText("runing..");
                jTextField2.setEnabled(false);
                jTextField3.setEnabled(true);
            }
            jFrame.setTitle("VVS web server - [" + status + "]");
        }
        if(status.contains("run"))
            webServer.update(port,root,maintenance,State.Running);
        else if(status.contains("stop"))
            webServer.update(port,root,maintenance,State.Stopped);
        else
            webServer.update(port,root,maintenance,State.Maintenance);
        System.out.println("Root:"+root);
        System.out.println("Maintenance:"+maintenance);
        System.out.println("Port:"+port);
    }

    public void setValidityRootDirectory() {
        if(!validatorRoot())
        {
            rootLabel.setIcon(new ImageIcon(image2));
        }
        else
        {
            rootLabel.setIcon(new ImageIcon(image1));
        }
    }
    public void setValidityMaintenanceDirectory() {
        if(!validatorMaintenance())
        {
            maintenanceLabel.setIcon(new ImageIcon(image2));
        }
        else
        {
            maintenanceLabel.setIcon(new ImageIcon(image1));
        }
    }

    public boolean validatorRoot()
    {
        if(root.charAt(root.length()-1)!='\\')
            root = root+"\\";
        String path = root +"index.html";
        File file = new File(path);
        if(file.isFile() && file.exists())
            return true;
        return false;
    }
    public boolean validatorMaintenance()
    {
        if(maintenance.charAt(maintenance.length()-1)!='\\')
            maintenance = maintenance+"\\";
        String path = maintenance  + "maintenance.html";
        System.out.println(path);
        File file = new File(path);
        if(file.isFile() && file.exists())
            return true;
        return false;
    }
}
