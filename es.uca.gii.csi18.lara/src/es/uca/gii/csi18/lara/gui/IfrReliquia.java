package es.uca.gii.csi18.lara.gui;

// import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import es.uca.gii.csi18.lara.data.*;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class IfrReliquia extends JInternalFrame {
    private JTextField txtNombre;
    private JTextField txtPeso;
    private Reliquia _reliquia = null;

    /**
     * Create the frame. 
     * @throws Exception 
     */
    public IfrReliquia(Reliquia reliquia) throws Exception {

        setResizable(true);
        setClosable(true);
        setTitle("Reliquia");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        txtNombre = new JTextField();
        txtNombre.setBounds(70, 51, 86, 20);
        getContentPane().add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(70, 33, 46, 14);
        getContentPane().add(lblNombre);

        txtPeso = new JTextField();
        txtPeso.setBounds(70, 97, 86, 20);
        getContentPane().add(txtPeso);
        txtPeso.setColumns(10);

        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setBounds(70, 82, 46, 14);
        getContentPane().add(lblPeso);
        
        JComboBox<Autor> cmbAutor = new JComboBox<Autor>();
        cmbAutor.setModel(
                new AutorListModel(Autor.Select()));
        cmbAutor.setBounds(70, 149, 86, 20);
        getContentPane().add(cmbAutor);
        
        JLabel lblAutor = new JLabel("Autor");
        lblAutor.setBounds(70, 128, 46, 14);
        getContentPane().add(lblAutor);

        JButton butGuardar = new JButton("Guardar");
        butGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               
                try {
                    if(cmbAutor.getModel().getSelectedItem()!=null) {
                        int iPeso = Integer.parseInt(txtPeso.getText());
                        String sNombre = txtNombre.getText();
                        Autor autor =(Autor) cmbAutor.getModel().getSelectedItem();
                        if (_reliquia == null) {
                            _reliquia = Reliquia.Create(iPeso, sNombre, autor);
                        } else {
                            _reliquia.setPeso(iPeso);
                            _reliquia.setNombre(sNombre);
                            _reliquia.setAutor(autor);
                            _reliquia.Update();
                        }
                    } else JOptionPane.showMessageDialog(null, "Debe seleccionar un autor", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Datos incorrectos", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        if (reliquia != null) {
            _reliquia = reliquia;
            txtPeso.setText(Integer.toString(reliquia.getPeso()));
            txtNombre.setText(reliquia.getNombre());
        }
        butGuardar.setBounds(70, 180, 89, 23);
        getContentPane().add(butGuardar);
    }
}
