/*
 * WebScarab.java
 *
 * Created on July 13, 2003, 7:11 PM
 */

package org.owasp.webscarab.ui.swing;

import org.owasp.webscarab.plugin.proxy.Proxy;
import org.owasp.webscarab.plugin.spider.Spider;
// import org.owasp.webscarab.*;
import org.owasp.webscarab.ui.swing.*;
import org.owasp.webscarab.ui.swing.proxy.ProxyPanel;
import org.owasp.webscarab.ui.swing.spider.SpiderPanel;

import java.util.ArrayList;

/**
 *
 * @author  rdawes
 */
public class WebScarabUI extends javax.swing.JFrame {
    
    private WebScarab _webscarab;
    private ArrayList _plugins;
    private SwingPlugin[] _pluginArray = new SwingPlugin[0];
    
    /** Creates new form WebScarab */
    public WebScarabUI(WebScarab webscarab) {
        _webscarab = webscarab;
        initComponents();
        
        // should instantiate a listener for Logger messages here, and insert it into the 
        // bottom part of the split pane . . .
        
        addPlugin(new ConversationLog(webscarab));

        Proxy proxy = new Proxy(webscarab);
        new Thread(proxy).start();
        
        webscarab.addPlugin(proxy);
        addPlugin(new ProxyPanel(proxy));
        
        Spider spider = new Spider(webscarab);
        webscarab.addPlugin(spider);
        addPlugin(new SpiderPanel(spider));

    }

    public void addPlugin(SwingPlugin plugin) {
        if (_plugins == null) {
            _plugins = new ArrayList();
        }
        _plugins.add(plugin);
        _pluginArray = (SwingPlugin[]) _plugins.toArray(_pluginArray);
        mainTabbedPane.add(plugin.getPanel(), plugin.getPluginName());
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        mainSplitPane = new javax.swing.JSplitPane();
        mainTabbedPane = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        toolsMenu = new javax.swing.JMenu();
        optionsMenuItem = new javax.swing.JMenuItem();
        saveConfigMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setTitle("WebScarab");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        mainSplitPane.setBorder(null);
        mainSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        mainSplitPane.setResizeWeight(1.0);
        mainSplitPane.setOneTouchExpandable(true);
        mainSplitPane.setLeftComponent(mainTabbedPane);

        logTextArea.setBackground(new java.awt.Color(204, 204, 204));
        logTextArea.setEditable(false);
        jScrollPane1.setViewportView(logTextArea);

        mainSplitPane.setRightComponent(jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(mainSplitPane, gridBagConstraints);

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");
        newMenuItem.setMnemonic('N');
        newMenuItem.setText("New");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(newMenuItem);

        openMenuItem.setMnemonic('O');
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(openMenuItem);

        saveAsMenuItem.setMnemonic('S');
        saveAsMenuItem.setText("Save As");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });

        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('X');
        exitMenuItem.setText("Exit");
        fileMenu.add(exitMenuItem);

        mainMenuBar.add(fileMenu);

        toolsMenu.setMnemonic('T');
        toolsMenu.setText("Tools");
        optionsMenuItem.setText("Options");
        toolsMenu.add(optionsMenuItem);

        saveConfigMenuItem.setText("Save Configuration");
        toolsMenu.add(saveConfigMenuItem);

        mainMenuBar.add(toolsMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText("Help");
        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });

        helpMenu.add(aboutMenuItem);

        mainMenuBar.add(helpMenu);

        setJMenuBar(mainMenuBar);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-640)/2, (screenSize.height-480)/2, 640, 480);
    }//GEN-END:initComponents

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        // get the dir from a filechooser
        String tempdir = "";
        for (int i=0; i<_pluginArray.length; i++) {
            _pluginArray[i].saveSession(tempdir);
        }
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        // get the dir from a filechooser
        String tempdir = "";
        for (int i=0; i<_pluginArray.length; i++) {
            _pluginArray[i].openSession(tempdir);
        }
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
        // get the dir from the temp environment variable ?
        String tempdir = "./scarab/temp";
        for (int i=0; i<_pluginArray.length; i++) {
            _pluginArray[i].newSession(tempdir);
        }
    }//GEN-LAST:event_newMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // FIXME
        System.out.println("Help/About not implemented yet!");
        System.out.println("OWASP WebScarab - part of the Open Web Application Security Project");
        System.out.println("See http://www.owasp.org/");
        System.out.println("Coders : Rogan Dawes (rdawes at telkomsa.net / rdawes at deloitte.co.za)");
        System.out.println("         Ingo Struck (ingo at ingostruck.de)");
    }//GEN-LAST:event_aboutMenuItemActionPerformed
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        // Should check to see if the model has been saved (somewhere where the 
        // user will find it, offer to rename the base directory to something
        // useful, before exiting.
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        WebScarab ws = new WebScarab();
        new WebScarabUI(ws).show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem optionsMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveConfigMenuItem;
    private javax.swing.JMenu toolsMenu;
    // End of variables declaration//GEN-END:variables
    
}
