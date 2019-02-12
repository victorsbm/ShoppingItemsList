/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppinglist;



import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Owner
 */
public class MainWindow extends javax.swing.JFrame {
    String imagePath = null;
    int itemIndex = 0;
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        /*use to test the connection to MySQL database at the beginning of programming, 
        Now, the getItemList() has been use the getConnection() already*/
        
        //getConnection(); 
        displayItemInTable();
    }
    
    //function to connect to mysql database called shopping_list_db
    public Connection getConnection(){
        Connection conToDatabase = null;
        
        try {
            conToDatabase = DriverManager.getConnection("jdbc:mysql://localhost/shopping_list_db", "root", "");
            //Use to test the connect in each action
            //JOptionPane.showMessageDialog(null, "Connected");
            return conToDatabase;
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Cannot Connected to database");
            return null;
        }
        
    }
    
    //To check the input is missing or not, shop address does not necessary  
    public boolean checkInput(){
        if(inputName.getText() == null
           || inputPrice.getText() == null
           || inputShopName.getText() == null
           || inputAddedDate.getText() == null){
                return false;
        } else {
            try{
                //in case the type of price input is different
                Float.parseFloat(inputPrice.getText());
                return true;
            }catch(Exception ex){
                return false;
            }
        }
    }
    
    //To resize the image from path, to able to display in image form
    public ImageIcon resizeImage(String imagePath, byte[] picture){
        ImageIcon imageDisplay = null;
        
        if(imagePath != null){
            imageDisplay = new ImageIcon(imagePath);
        } else {
            imageDisplay = new ImageIcon(picture);
        }
        Image origalImage = imageDisplay.getImage();
        Image resizeImage = origalImage.getScaledInstance(imageDisplayForm.getWidth(), imageDisplayForm.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(resizeImage);
        return image;
        
        
    }
    
    //get the items from the sql database and return
    public ArrayList<Items> getItemList(){
            ArrayList<Items> itemList  = new ArrayList<Items>();
            Connection conToDatabase = getConnection();
            String sqlQuery = "SELECT * FROM items";
            
            Statement statementToDisplay;
            ResultSet resultDisplay;
            
        try {
            
            statementToDisplay = conToDatabase.createStatement();
            resultDisplay = statementToDisplay.executeQuery(sqlQuery);
            Items item;
            while(resultDisplay.next()){
                item = new Items(resultDisplay.getInt("id"),resultDisplay.getString("name"),Float.parseFloat(resultDisplay.getString("prices")),resultDisplay.getString("shop_name"),resultDisplay.getString("shop_address"),resultDisplay.getString("added_date"),resultDisplay.getBytes("image"));
                itemList.add(item);
            }       
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            } 
        return itemList; 
    }
    //display the items into table
    public void displayItemInTable(){
        ArrayList<Items> list = getItemList();
        DefaultTableModel model = (DefaultTableModel)itemsTable.getModel();

        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getID();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getShop();
            row[4] = list.get(i).getAddress();
            row[5] = list.get(i).getAddedDate();
       
            model.addRow(row);
            }  
    }

    //To display the item information into input text field
    public void showItemInput(int index){
        textID.setText(Integer.toString(getItemList().get(index).getID()));
        inputName.setText(getItemList().get(index).getName());
        inputPrice.setText(Float.toString(getItemList().get(index).getPrice()));
        inputShopName.setText(getItemList().get(index).getShop());
        inputAddress.setText(getItemList().get(index).getAddress());
        inputAddedDate.setText(getItemList().get(index).getAddedDate());
        imageDisplayForm.setIcon(resizeImage(null, getItemList().get(index).getImage()));
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textID = new javax.swing.JTextField();
        inputAddedDate = new javax.swing.JTextField();
        inputAddress = new javax.swing.JTextField();
        inputShopName = new javax.swing.JTextField();
        inputName = new javax.swing.JTextField();
        inputPrice = new javax.swing.JTextField();
        imageDisplayForm = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemsTable = new javax.swing.JTable();
        chooseImageButton = new javax.swing.JButton();
        insertButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        firstButton = new javax.swing.JButton();
        lastButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(240, 240, 170));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Shop:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ID:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Name:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Price:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Address:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Date added:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Image:");

        textID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        textID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIDActionPerformed(evt);
            }
        });

        inputAddedDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        inputAddedDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputAddedDateActionPerformed(evt);
            }
        });

        inputAddress.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        inputShopName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        inputName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        inputPrice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        imageDisplayForm.setBackground(new java.awt.Color(153, 204, 255));
        imageDisplayForm.setOpaque(true);

        itemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Shop", "Address", "Date added"
            }
        ));
        itemsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        itemsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(itemsTable);

        chooseImageButton.setText("Choose image");
        chooseImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseImageButtonActionPerformed(evt);
            }
        });

        insertButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        insertButton.setText("Insert");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        removeButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        removeButton.setText("Remove");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        nextButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        previousButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        previousButton.setText("Previous");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        firstButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        firstButton.setText("First");
        firstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstButtonActionPerformed(evt);
            }
        });

        lastButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lastButton.setText("Last");
        lastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(inputAddedDate)
                                .addComponent(inputAddress)
                                .addComponent(inputShopName)
                                .addComponent(inputPrice)
                                .addComponent(inputName)
                                .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(imageDisplayForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(chooseImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 170, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(insertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(firstButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(inputName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputShopName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputAddedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imageDisplayForm, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chooseImageButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insertButton, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(removeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nextButton)
                                .addComponent(previousButton))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(firstButton)
                                .addComponent(lastButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textIDActionPerformed

    private void chooseImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseImageButtonActionPerformed
        // TODO add your handling code here:
JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
       
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            imageDisplayForm.setIcon(resizeImage(path, null));
            imagePath = path;
        } else {
            System.out.println("There is no File Selected");
        }
    }//GEN-LAST:event_chooseImageButtonActionPerformed

    private void inputAddedDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputAddedDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputAddedDateActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
        // TODO add your handling code here:
        if(checkInput() && imagePath != null)
        {
            try {
                Connection conToDatabase = getConnection();
                PreparedStatement sqlStatment = conToDatabase.prepareStatement("INSERT INTO items(name, prices, shop_name, shop_address, added_date, image)"
                        + "values(?,?,?,?,?,?) ");
                sqlStatment.setString(1, inputName.getText());
                sqlStatment.setString(2, inputPrice.getText());
                sqlStatment.setString(3, inputShopName.getText());
                sqlStatment.setString(4, inputAddress.getText());
                sqlStatment.setString(5, inputAddedDate.getText());

               
                InputStream img = new FileInputStream(new File(imagePath));
                sqlStatment.setBlob(6, img);
                sqlStatment.executeUpdate();
                displayItemInTable();
               
                JOptionPane.showMessageDialog(null, "Data Inserted");
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Some inputs are missing");
        }
       

        
    }//GEN-LAST:event_insertButtonActionPerformed

    
    //To insert the item information into sql database
    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        if(checkInput() && textID != null){
            String sqlQuery = null;
            PreparedStatement sqlStatment = null;
            Connection conToDatabase = getConnection();
            if(imagePath == null){
                try{
                    //the name of the table is items in the database 
                    sqlQuery = "UPDATE items SET name = ?, prices = ?"
                            + ", shop_name = ?, shop_address = ?"
                            + ",added_date = ? WHERE id = ?"; 
                    sqlStatment = conToDatabase.prepareStatement(sqlQuery);
                    
                    sqlStatment.setString(1, inputName.getText());
                    sqlStatment.setString(2, inputPrice.getText());
                    sqlStatment.setString(3, inputShopName.getText());
                    sqlStatment.setString(4, inputAddress.getText());
                    sqlStatment.setString(5, inputAddedDate.getText());
                    
                    sqlStatment.setInt(6, Integer.parseInt(textID.getText()));
                    
                    sqlStatment.executeUpdate();
                    displayItemInTable();
                    JOptionPane.showMessageDialog(null, "Items updated");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                //update with image
                try {
                    InputStream image = new FileInputStream(new File(imagePath));
                    sqlQuery = "UPDATE items SET name = ?, prices = ?"
                            + ", shop_name = ?, shop_address = ?"
                            + ",added_date = ?, image = ? WHERE id = ?"; 
                    sqlStatment = conToDatabase.prepareStatement(sqlQuery);
                    
                    sqlStatment.setString(1, inputName.getText());
                    sqlStatment.setString(2, inputPrice.getText());
                    sqlStatment.setString(3, inputShopName.getText());
                    sqlStatment.setString(4, inputAddress.getText());
                    sqlStatment.setString(5, inputAddedDate.getText());
                    sqlStatment.setBlob(6, image);
                    sqlStatment.setInt(7, Integer.parseInt(textID.getText()));
                    
                    sqlStatment.executeUpdate();
                    displayItemInTable();
                    JOptionPane.showMessageDialog(null, "Items updated");
                    
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }  
        } else {
             JOptionPane.showMessageDialog(null, "Some nputs are missing");
        } 
    }//GEN-LAST:event_updateButtonActionPerformed

    
    //To remove(or delete) the item from database when the id of item
    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
        if(!textID.getText().equals("")){
            try {
                Connection conToDatabase = getConnection();
                PreparedStatement sqlStatment = conToDatabase.prepareStatement("DELETE FROM items WHERE id = ?");
                int inputID = Integer.parseInt(textID.getText());
                sqlStatment.setInt(1, inputID);
                sqlStatment.executeUpdate();
                displayItemInTable();
                JOptionPane.showMessageDialog(null, "Item has been deleted");
            } catch (SQLException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Item cannot been deleted");
            }
         
        } else {
            JOptionPane.showMessageDialog(null, "Item ID is mismatching");
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    //Able the click the items from table using the mouse
    private void itemsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsTableMouseClicked
        // TODO add your handling code here:
        int index = itemsTable.getSelectedRow();
        showItemInput(index);
        
    }//GEN-LAST:event_itemsTableMouseClicked
    
    //To display information of the first item in table
    private void firstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstButtonActionPerformed
        // TODO add your handling code here:
        itemIndex = 0;
        showItemInput(itemIndex);
        
    }//GEN-LAST:event_firstButtonActionPerformed
    //To display information of the last item in table
    private void lastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastButtonActionPerformed
        // TODO add your handling code here:
        itemIndex = getItemList().size()-1;
        showItemInput(itemIndex);
    }//GEN-LAST:event_lastButtonActionPerformed

    //To display next from current item information
    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        itemIndex++;
        if(itemIndex >= getItemList().size()){
            itemIndex = getItemList().size()-1;
        }
        showItemInput(itemIndex);
    }//GEN-LAST:event_nextButtonActionPerformed
    
   //To display the previous item of current item
    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        // TODO add your handling code here:
        itemIndex--;
        if(itemIndex < 0){
            itemIndex = 0;
        }
        showItemInput(itemIndex);
    }//GEN-LAST:event_previousButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseImageButton;
    private javax.swing.JButton firstButton;
    private javax.swing.JLabel imageDisplayForm;
    private javax.swing.JTextField inputAddedDate;
    private javax.swing.JTextField inputAddress;
    private javax.swing.JTextField inputName;
    private javax.swing.JTextField inputPrice;
    private javax.swing.JTextField inputShopName;
    private javax.swing.JButton insertButton;
    private javax.swing.JTable itemsTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lastButton;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JTextField textID;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
