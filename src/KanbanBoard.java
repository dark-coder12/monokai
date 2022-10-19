import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class KanbanBoard extends JDialog {
    private JButton homeButton;
    AddTask myTask;
    private JButton kanbanBoardButton;
    private JButton ganttChartButton;
    private JPanel kanbanPanel;
    private JButton logOutButton;
    private JButton addTaskButton;
    private JPanel toDoColList;

    Column toDoColumn = new Column();
    Column inProgressColumn = new Column();
    Column completedColumn = new Column();

    KanbanBoard(JFrame parent) {

        setTitle("WorkspaceKanban");

        setContentPane(kanbanPanel);

        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(parent);

        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setFocusable(false);

        kanbanBoardButton.setBorderPainted(false);
        kanbanBoardButton.setContentAreaFilled(false);
        kanbanBoardButton.setFocusable(false);

        ganttChartButton.setBorderPainted(false);
        ganttChartButton.setContentAreaFilled(false);
        ganttChartButton.setFocusable(false);

        logOutButton.setBorderPainted(false);
        logOutButton.setContentAreaFilled(false);
        logOutButton.setFocusable(false);

        addTaskButton.setBorderPainted(false);
        addTaskButton.setContentAreaFilled(false);
        addTaskButton.setFocusable(false);

        setVisible(true);

        homeButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                homeButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                homeButton.setContentAreaFilled(false);
            }
        });

        ganttChartButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                ganttChartButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ganttChartButton.setContentAreaFilled(false);
            }
        });

        logOutButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                logOutButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logOutButton.setContentAreaFilled(false);
            }
        });

        kanbanBoardButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                kanbanBoardButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                kanbanBoardButton.setContentAreaFilled(false);
            }
        });

        addTaskButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                addTaskButton.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addTaskButton.setContentAreaFilled(false);
            }
        });

        addTaskButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                myTask = new AddTask(null);

                myTask.applyButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        myTask.name = myTask.textField1.getText();
                        myTask.priority = myTask.priorityName.getSelectedItem().toString();
                        myTask.columnVal = myTask.columnName.getSelectedItem().toString();

                        if (myTask.columnVal.equals("To Do")) {
                            toDoColumn.addTaskInColumn(myTask.name, myTask.priority);
                            toDoColumn.print();
                        } else if (myTask.columnVal.equals("In Progress")) {
                            inProgressColumn.addTaskInColumn(myTask.name, myTask.priority);
                            inProgressColumn.print();
                        } else if (myTask.columnVal.equals("Completed")) {
                            completedColumn.addTaskInColumn(myTask.name, myTask.priority);
                            completedColumn.print();
                        }

                        initializeBoard();
                    }
                });
            }
        });
    }

    public void initializeBoard() {

        toDoColList.removeAll();

        GridBagLayout g = new GridBagLayout();
        toDoColList.setLayout(g);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 5, 10, 4);


        for (int i = 0; i < toDoColumn.allTasks.size(); i++) {

            JLabel taskName = new JLabel(toDoColumn.allTasks.get(i).name);

            taskName.setForeground(Color.black);
            taskName.setSize(taskName.getPreferredSize());
            toDoColList.add(taskName, constraints);
            constraints.gridy = constraints.gridy + 1;
            toDoColList.revalidate();
            toDoColList.repaint();
            toDoColList.setVisible(true);
            Border blackline = BorderFactory.createLineBorder(Color.black);
            taskName.setBorder(blackline);
            taskName.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    JPopupMenu menu = new JPopupMenu();

                    menu.add("Delete");
                    menu.add("Edit").addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            EditTask myEdit = new EditTask(null);
                        }
                        });

                    menu.show(e.getComponent(), e.getX(), e.getY());
                    menu.setVisible(true);
                }
            });
        }
    }

    public static void main(String[] args) {

        KanbanBoard loginForm = new KanbanBoard(null);
    }
}