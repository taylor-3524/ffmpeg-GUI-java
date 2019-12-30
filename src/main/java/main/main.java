package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
            frame f= new frame();
            f.myFrame();

    }
    public static class frame extends JFrame {
        String startTime = null;
        String endTime = null;
        String originFile = null;
        String outFile = null;
        static String cmdStr=null;

        void myFrame() {
            Frame frame = new JFrame("ffmepgGUI");
            frame.setSize(450, 260);
            setLayout(new BorderLayout(10,10));
            JPanel p[] = new JPanel[3];
            for (int i = 0; i < 3; i++) {
                p[i] = new JPanel();
            }
            frame.add(p[0],BorderLayout.NORTH);
            frame.add(p[1]);
            frame.add(p[2],BorderLayout.SOUTH);


            p[0].setLayout(new FlowLayout());
            JLabel headLab = new JLabel("急速无损切割音视频");
            headLab.setFont(new Font("黑体", Font.BOLD , 40));
            p[0].add(headLab);


            Font tipFont=new Font("宋体", Font.BOLD , 20);
            p[1].setLayout(new GridLayout(4, 2));
            JLabel startTip = new JLabel("请输入起始时间");
            startTip.setFont(tipFont);
            p[1].add(startTip);
            final JTextField start=new JTextField(8);
            p[1].add(start);
            JLabel endTip = new JLabel("请输入结束时间");
            endTip.setFont(tipFont);
            p[1].add(endTip);
            final JTextField end=new JTextField(8);
            p[1].add(end);
            JLabel originTip = new JLabel("请输入源文件名");
            p[1].add(originTip);
            originTip.setFont(tipFont);
            final JTextField origin=new JTextField();
            p[1].add(origin);
            JLabel OutFileTip = new JLabel("请输入要输出的文件名");
            p[1].add(OutFileTip);
            OutFileTip.setFont(tipFont);
            final JTextField OutFile=new JTextField();
            p[1].add(OutFile);

            p[2].setLayout(new FlowLayout());
            JButton submit=new JButton("切割");
            submit.setFont(tipFont);
            Dimension preferredSize=new Dimension(450,30);
            submit.setPreferredSize(preferredSize);
            p[2].add(submit);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);



            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startTime=start.getText();
                    endTime=end.getText();
                    originFile=origin.getText();
                    outFile=OutFile.getText();
                    cmdStr="cmd /k ffmpeg -ss " + startTime + " -to " + endTime + " -accurate_seek -i " + originFile + " -codec copy -avoid_negative_ts 1 " + outFile;

                    try {
                        Runtime.getRuntime().exec(cmdStr);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            });
        }
    }
}
