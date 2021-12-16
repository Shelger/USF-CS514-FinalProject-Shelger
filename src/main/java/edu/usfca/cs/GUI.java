package edu.usfca.cs;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

public class GUI{
    public void showGUI(){
        JFrame frame = new JFrame("MUSIC");

        final JLabel label1 = new JLabel("Library");
        final JButton button = new JButton("ADD");
        final JLabel label2 = new JLabel();
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        //panel.setLayout(new GridLayout(1,0));
        final DefaultTableModel model = createTable();
        final JTable table = new JTable(model);
        JScrollPane tablePane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(500,300));
        table.setFillsViewportHeight(true);
        panel.add(label1);
        panel.add(tablePane);
        panel.add(button);
        panel.add(label2);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                String result = (String) JOptionPane.showInputDialog(
                        frame,
                        "Please enter the name of the song:",
                        "ADD",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        ""
                );
                if (result != null && result.length() > 0) {
                    Song adding = FromMusicBrainz.readingSong(result);
                    model.addRow(new Object[]{adding.getName(),adding.getEntityID(),adding.getAlbum().getName(),adding.getArtist().getName()});
                } else {
                    label2.setText("No song added.");
                }
            }
        });

        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.pack();
        frame.setVisible(true);
    }

    public DefaultTableModel createTable(){
        String[] attributes = {"Song","Song ID","Album","Artists"};
        Object[][] data;
        ArrayList<Song> temp = FromSQL.songsFromSQL();
        data = new Object[temp.size()][4];
        for(int i = 0 ; i<temp.size(); i++){
            Song song = temp.get(i);
            data[i][0] = song.getName();
            data[i][1] = song.getEntityID();
            data[i][2] = song.getAlbum().getName();
            data[i][3] = song.getArtist().getName();
        }
        final DefaultTableModel df = new DefaultTableModel(data,attributes);
        return df;
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.showGUI();
    }
}
