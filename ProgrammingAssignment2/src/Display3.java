import javax.swing.*;
import java.awt.*;

public class Display3 extends JFrame
{
   private JFrame frame3; //Used for the third display
   private JPanel inRoomPanel, addRoomPanel, westPanel, addRemoveButtonPanel, roomPanel, eastPanel;
   private JLabel itemsInRoomLabel, addItemsInRoomLabel;
   private JLabel spacing1, spacing2, spacing3, spacing4, spacing5, spacing6, spacing7;
   private DefaultListModel itemsInRoom, addItemsInRoom;
   private JList itemsInRoomList, addItemsInRoomList;
   private JScrollPane itemsInRoomScrollPane, addItemsInRoomScrollPane;
   private JButton addToRoomButton, removeFromRoomButton;
   private JButton[] roomNumbers;
   private int amt_of_rooms = 9;
   
   public static void main(String[] args)
   {
      Display3 run = new Display3();
   }
   
   public Display3()
   {
      frame3 = new JFrame();
      frame3.setTitle("Alexander Whitsell Display 3"); // Add a title here
      frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the GUI 
      frame3.setResizable(false); // Viewer cannot change the size
      frame3.setLayout(new BorderLayout());
      
         /* This display listed Items & Creatures in the room */
         inRoomPanel = new JPanel();
         inRoomPanel.setLayout(new BorderLayout());
         spacing1 = new JLabel("     "); spacing2 = new JLabel("     ");
         itemsInRoomLabel = new JLabel("<html><font size=2>ITEMS / CREATURES <br>LOCATED IN ROOM</font></html>", SwingConstants.CENTER);
         itemsInRoom = new DefaultListModel();
         itemsInRoom.addElement(" "); // Create a for loop, list all elements in the room.
         itemsInRoomList = new JList(itemsInRoom);
         itemsInRoomScrollPane = new JScrollPane();
         itemsInRoomScrollPane.setViewportView(itemsInRoomList);
         itemsInRoomScrollPane.setPreferredSize(new Dimension(50,100));
         inRoomPanel.add(itemsInRoomLabel, BorderLayout.NORTH);
         inRoomPanel.add(spacing1, BorderLayout.WEST);
         inRoomPanel.add(spacing2, BorderLayout.EAST);
         inRoomPanel.add(itemsInRoomScrollPane, BorderLayout.CENTER);
      
      
         /* This displays Add & Remove items in the room */
         addRoomPanel = new JPanel();
         addRoomPanel.setLayout(new BorderLayout());
         addItemsInRoomLabel = new JLabel("<html>  <font size=2>ADD / REMOVE TO ROOM</font>  </html>", SwingConstants.CENTER);
         addItemsInRoom = new DefaultListModel();
         addItemsInRoom.addElement(" "); // Create a for loop, list all elements in the room.
         addItemsInRoomList = new JList(addItemsInRoom);
         addItemsInRoomScrollPane = new JScrollPane();
         addItemsInRoomScrollPane.setViewportView(addItemsInRoomList);
         addItemsInRoomScrollPane.setPreferredSize(new Dimension(50,50));
         addRoomPanel.add(addItemsInRoomLabel, BorderLayout.NORTH);
         addRoomPanel.add(addItemsInRoomScrollPane, BorderLayout.CENTER);
         
         /* This allows the user to add or remove items/creatures in the room */
         addRemoveButtonPanel = new JPanel();
         addRemoveButtonPanel.setLayout(new BorderLayout());
         addToRoomButton = new JButton("ADD");
         removeFromRoomButton = new JButton("REMOVE");
         addRemoveButtonPanel.add(addToRoomButton, BorderLayout.EAST);
         addRemoveButtonPanel.add(removeFromRoomButton, BorderLayout.WEST);
         
      eastPanel = new JPanel();
      spacing3 = new JLabel("     "); spacing4 = new JLabel("     ");
      eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      eastPanel.setLayout(new BorderLayout());
      eastPanel.add(inRoomPanel, BorderLayout.NORTH);
      eastPanel.add(spacing3, BorderLayout.EAST);
      eastPanel.add(addRoomPanel, BorderLayout.CENTER);
      eastPanel.add(spacing4, BorderLayout.WEST);
      eastPanel.add(addRemoveButtonPanel, BorderLayout.SOUTH);
      
         /* This are the rooms that the user will select. */
         roomPanel = new JPanel();
         roomPanel.setLayout(new GridLayout(3,3));
         roomNumbers = new JButton[amt_of_rooms];
         for (int i = 0; i < amt_of_rooms; i++) {
            roomNumbers[i]=new JButton("Room #"+(i+1));
            roomPanel.add(roomNumbers[i]);
         }
      
      
      westPanel = new JPanel();
      westPanel.setLayout(new BorderLayout());
      spacing5 = new JLabel("     "); spacing6 = new JLabel("     "); spacing7 = new JLabel("     ");
      westPanel.add(roomPanel, BorderLayout.CENTER);
      westPanel.add(spacing5, BorderLayout.NORTH);
      westPanel.add(spacing6, BorderLayout.SOUTH);
      westPanel.add(spacing7, BorderLayout.WEST);
      
      frame3.add(westPanel, BorderLayout.WEST);
      frame3.add(eastPanel, BorderLayout.EAST);
      frame3.setSize(450,300);
      frame3.setVisible(true);
   }
}
