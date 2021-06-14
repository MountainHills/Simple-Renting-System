package GUI;

import excelSheet.RentFileGenerator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import parameters.RentInformation;

public class ProcessedGUI extends javax.swing.JFrame {
    
    public ProcessedGUI() {
        initComponents();
        
        String totalBill = String.format("%.2f", RentInformation.getTotalBill());
        totalTextField.setText(totalBill);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        confirmPane = new javax.swing.JOptionPane();
        jLabel1 = new javax.swing.JLabel();
        excelButton = new javax.swing.JButton();
        totalTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Total");

        excelButton.setText("Get Excel File");
        excelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelButtonActionPerformed(evt);
            }
        });

        totalTextField.setColumns(61);
        totalTextField.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(excelButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(excelButton)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void excelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelButtonActionPerformed
        // Call new class of Excel here.
        
        int choice = confirmPane.showConfirmDialog(null ,"Are you sure you want to create an Excel Workbook?", "Confirm", confirmPane.YES_NO_OPTION);
        
        if (choice == confirmPane.YES_OPTION){  
            try
            {
                new RentFileGenerator();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Failed to create file. Close the Excel File.");
            }
        }  
        
        // Confirmation Panel.
    }//GEN-LAST:event_excelButtonActionPerformed

    public void launchFrame()
    {
        setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JOptionPane confirmPane;
    private javax.swing.JButton excelButton;
    private javax.swing.JLabel jLabel1;
    private static javax.swing.JTextField totalTextField;
    // End of variables declaration//GEN-END:variables
}
