package es.uca.gii.csi18.lara.gui;

// import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import es.uca.gii.csi18.lara.gui.ReliquiasTableModel;
import es.uca.gii.csi18.lara.data.Autor;
import es.uca.gii.csi18.lara.data.Reliquia;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class IfrReliquias extends JInternalFrame {
    private JTable tabResult;
    private JTextField txtNombre;
    private JTextField txtPeso;
    private Container pnlParent;

    /**
     * Create the frame.
     * 
     * @throws Exception
     */
    public IfrReliquias(Container frame) throws Exception {

        pnlParent = frame;
        setClosable(true);
        setResizable(true);
        setTitle("Reliquias");
        setBounds(100, 100, 351, 263);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(5, 2, 0, 0));

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        panel.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblPeso);

        txtPeso = new JTextField();
        panel.add(txtPeso);
        txtPeso.setColumns(10);

        JLabel lblAutor = new JLabel("Autor");
        lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblAutor);

        JComboBox<Autor> cmbAutor = new JComboBox<Autor>();
        cmbAutor.setModel(new AutorListModel(Autor.Select()));
        cmbAutor.setEditable(true);
        panel.add(cmbAutor);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer iPeso = null;
                Autor autor = null;
                if (!txtPeso.getText().isEmpty())
                    iPeso = Integer.parseInt(txtPeso.getText());
                String sNombre = txtNombre.getText();
                if (cmbAutor.getModel().getSelectedItem() != null)
                    autor = (Autor) cmbAutor.getModel().getSelectedItem();
                try {

                    tabResult.setModel(new ReliquiasTableModel(
                            Reliquia.Select(iPeso, sNombre, autor.toString())));

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "No se encuentra", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(btnBuscar);

        tabResult = new JTable();
        tabResult.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                // Se activa con doble clic sobre una fila
                if (arg0.getClickCount() == 2) {
                    int iRow = ((JTable) arg0.getSource()).getSelectedRow();
                    Reliquia reliquia = null;
                    try {
                        reliquia = ((ReliquiasTableModel) tabResult.getModel())
                                .getData(iRow);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Error generando la tabla", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (reliquia != null) {
                        IfrReliquia ifrReliquia = null;
                        try {

                            ifrReliquia = new IfrReliquia(reliquia);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        ifrReliquia.setBounds(10, 27, 244, 192);
                        pnlParent.add(ifrReliquia, 0);
                        ifrReliquia.setVisible(true);
                    }
                }
            }
        });
        getContentPane().add(tabResult, BorderLayout.CENTER);
    }
}
