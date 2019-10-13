package es.uca.gii.csi18.lara.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMain {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmMain window = new FrmMain();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public FrmMain() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Mansi\u00F3n Richard Croft");
        frame.setBounds(200, 200, 550, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mitNuevo = new JMenu("Nuevo");
        menuBar.add(mitNuevo);

        JMenuItem mitNuevaReliquia = new JMenuItem("Reliquia");
        mitNuevaReliquia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                IfrReliquia ifrReliquia = null;
                try {
                    ifrReliquia = new IfrReliquia(null);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                ifrReliquia.setBounds(10, 27, 244, 192);
                frame.getContentPane().add(ifrReliquia);
                ifrReliquia.setVisible(true);
            }
        });
        mitNuevo.add(mitNuevaReliquia);

        JMenu mitBuscar = new JMenu("Buscar");
        menuBar.add(mitBuscar);

        JMenuItem mitBuscarReliquia = new JMenuItem("Reliquia");
        mitBuscarReliquia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                IfrReliquias ifrReliquias = null;
                try {
                    ifrReliquias = new IfrReliquias(frame);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ifrReliquias.setBounds(12, 28, 244, 192);
                frame.getContentPane().add(ifrReliquias, 0);
                ifrReliquias.setVisible(true);
            }
        });
        mitBuscar.add(mitBuscarReliquia);
        frame.getContentPane().setLayout(null);
    }
}
