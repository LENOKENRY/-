import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// 课程二叉树节点
class CourseNode {
    String courseName;
    int courseScore;
    String courseUrl;
    CourseNode left, right;

    public CourseNode(String courseName, int courseScore, String courseUrl) {
        this.courseName = courseName;
        this.courseScore = courseScore;
        this.courseUrl = courseUrl;
        this.left = this.right = null;
    }
}

// 课程二叉树
class CourseBinaryTree {
    private CourseNode root;

    public CourseBinaryTree() {
        this.root = null;
    }

    // 插入课程信息（按照课程名称中的数字进行插入）
    public void insertCourse(String courseName, int courseScore, String courseUrl) {
        int courseNumber = extractCourseNumber(courseName);  // 提取课程名称中的数字
        root = insertCourseRec(root, courseNumber, courseScore, courseUrl);
    }

    // 递归插入课程（按照课程数字进行插入）
    private CourseNode insertCourseRec(CourseNode root, int courseNumber, int courseScore, String courseUrl) {
        if (root == null) {
            root = new CourseNode("课程 " + courseNumber, courseScore, courseUrl);
            return root;
        }

        // 根据课程数字进行比较
        if (courseNumber < extractCourseNumber(root.courseName)) {
            root.left = insertCourseRec(root.left, courseNumber, courseScore, courseUrl);
        } else if (courseNumber > extractCourseNumber(root.courseName)) {
            root.right = insertCourseRec(root.right, courseNumber, courseScore, courseUrl);
        }

        return root;
    }

    // 提取课程名称中的数字部分（课程 1, 课程 2, ...）
    private int extractCourseNumber(String courseName) {
        // 假设课程名称格式为 "课程 X"，提取数字 X
        String[] parts = courseName.split(" ");
        return Integer.parseInt(parts[1]);
    }

    // 查找课程信息（根据课程名称中的数字）
    public CourseNode findCourse(int courseNumber) {
        return findCourseRec(root, courseNumber);
    }

    // 递归查找课程（根据课程名称中的数字）
    private CourseNode findCourseRec(CourseNode root, int courseNumber) {
        // 如果树为空或找到了目标课程
        if (root == null || extractCourseNumber(root.courseName) == courseNumber) {
            return root;
        }

        // 如果目标课程数字较小，继续在左子树查找
        if (courseNumber < extractCourseNumber(root.courseName)) {
            return findCourseRec(root.left, courseNumber);
        }

        // 否则，在右子树查找
        return findCourseRec(root.right, courseNumber);
    }
}


// 用户类，存储用户信息以及已选课程
class user {
    String name = "ab";
    String passw = "1234";
    String ab1, ab2, ab3, ab4, ab5, ab6, ab7, ab8, ab9, ab10;

    // 用来存储已选择的课程
    ArrayList<String> selectedCourses = new ArrayList<>();

    user() {}

    user(String name, String passw) {
        this.name = name;
        this.passw = passw;
    }

    // 设置ab1到ab10的值
    public void setBody(String ab1, String ab2, String ab3, String ab4, String ab5, String ab6, String ab7, String ab8, String ab9, String ab10) {
        this.ab1 = ab1;
        this.ab2 = ab2;
        this.ab3 = ab3;
        this.ab4 = ab4;
        this.ab5 = ab5;
        this.ab6 = ab6;
        this.ab7 = ab7;
        this.ab8 = ab8;
        this.ab9 = ab9;
        this.ab10 = ab10;
    }

    // 获取所有 ab 字段的值
    public String[] getBody() {
        return new String[]{ab1, ab2, ab3, ab4, ab5, ab6, ab7, ab8, ab9, ab10};
    }

    // 添加已选课程
    public void addSelectedCourse(String course) {
        if (!selectedCourses.contains(course)) {
            selectedCourses.add(course);
        }
    }

    // 获取已选课程
    public ArrayList<String> getSelectedCourses() {
        return selectedCourses;
    }

    // 将所选课程的分数加到对应的 ab 字段
    public void addScoreToBody(int x,int courseIndex) {
        int score = courseIndex;  // 假设课程分数等于课程编号
        switch (x) {
            case 1: ab1 = Integer.toString(Integer.parseInt(ab1 == null ? "0" : ab1) + score); break;
            case 2: ab2 = Integer.toString(Integer.parseInt(ab2 == null ? "0" : ab2) + score); break;
            case 3: ab3 = Integer.toString(Integer.parseInt(ab3 == null ? "0" : ab3) + score); break;
            case 4: ab4 = Integer.toString(Integer.parseInt(ab4 == null ? "0" : ab4) + score); break;
            case 5: ab5 = Integer.toString(Integer.parseInt(ab5 == null ? "0" : ab5) + score); break;
            case 6: ab6 = Integer.toString(Integer.parseInt(ab6 == null ? "0" : ab6) + score); break;
            case 7: ab7 = Integer.toString(Integer.parseInt(ab7 == null ? "0" : ab7) + score); break;
            case 8: ab8 = Integer.toString(Integer.parseInt(ab8 == null ? "0" : ab8) + score); break;
            case 9: ab9 = Integer.toString(Integer.parseInt(ab9 == null ? "0" : ab9) + score); break;
            case 10: ab10 = Integer.toString(Integer.parseInt(ab10 == null ? "0" : ab10) + score); break;
        }
    }

    // 获取最低的 ab 值及其对应的课程
    public int getLowestAbCourse() {
        String[] abValues = getBody();  // 获取 ab1 到 ab10 的所有值
        int minScore = Integer.MAX_VALUE;
        int minIndex = -1;
        // 找出 ab1 到 ab10 中最小的值
        for (int i = 0; i < abValues.length; i++) {
            int score = Integer.parseInt(abValues[i] == null ? "0" : abValues[i]);
            if (score < minScore) {
                minScore = score;
                minIndex = i;
            }
        }

        // 根据最小值的下标，返回推荐课程（课程与 ab 对应关系）
        return (minIndex + 1);  // 课程 1 到 10 对应 ab1 到 ab10
    }
}

// 登录界面
class frame1 extends Frame implements ActionListener, WindowListener {
    Label usernameLabel;
    TextField usernameField;
    Label passwordLabel;
    TextField passwordField;
    Button loginButton;
    Label tishi;
    user u1;
    Panel p1;
    Panel p2;
    Button xuanke;
    Button chakan;
    Button chushihua;
    Button yixuekecheng;
    Button tuijian;  // 新增按钮
    f4 f4Window;  // 新增 f4 窗口实例

    frame1(user u) {
        super("健身管理系统登录");
        u1 = u;
        setSize(1000, 600);
        setLayout(new GridLayout(2, 1));
        Font font = new Font("宋体", Font.BOLD, 20);
        setFont(font);

        // 创建组件
        usernameLabel = new Label("用户名:");
        usernameField = new TextField(10);
        passwordLabel = new Label("密码:");
        passwordField = new TextField(10);
        tishi = new Label(" ");
        loginButton = new Button("登录");
        chushihua = new Button("初始化");
        xuanke = new Button("选课");
        chakan = new Button("查看数值");
        yixuekecheng = new Button("已学课程");
        tuijian = new Button("推荐课程");  // 创建推荐课程按钮
        p1 = new Panel();
        p2 = new Panel();

        // 添加组件到框架
        p1.add(usernameLabel);
        p1.add(usernameField);
        p1.add(passwordLabel);
        p1.add(passwordField);
        p1.add(loginButton);
        p1.add(tishi);

        p2.add(chushihua);
        p2.add(xuanke);
        p2.add(chakan);
        p2.add(yixuekecheng);
        p2.add(tuijian);  // 将推荐课程按钮添加到面板

        chushihua.setEnabled(false);
        xuanke.setEnabled(false);
        chakan.setEnabled(false);
        yixuekecheng.setEnabled(false);
        tuijian.setEnabled(false);  // 初始时禁用推荐课程按钮

        add(p1);
        add(p2);

        // 显示框架
        setLocation(800, 150); // 居中显示
        setVisible(true);

        usernameField.addActionListener(this);
        passwordField.addActionListener(this);
        loginButton.addActionListener(this);
        addWindowListener(this);
        chushihua.addActionListener(this);  // 监听初始化按钮
        chakan.addActionListener(this);  // 监听查看数值按钮
        xuanke.addActionListener(this);  // 监听选课按钮
        yixuekecheng.addActionListener(this);  // 监听已学课程按钮
        tuijian.addActionListener(this);  // 监听推荐课程按钮
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (usernameField.getText().equals(u1.name) && passwordField.getText().equals(u1.passw)) {
                tishi.setText("欢迎你，" + u1.name + "!");
                chushihua.setEnabled(true);
                xuanke.setEnabled(true);
                chakan.setEnabled(true);
                yixuekecheng.setEnabled(true);
                tuijian.setEnabled(true);  // 登录成功后启用推荐课程按钮
            } else {
                tishi.setText("用户名或密码错！");
                chushihua.setEnabled(false);
                xuanke.setEnabled(false);
                chakan.setEnabled(false);
                yixuekecheng.setEnabled(false);
                tuijian.setEnabled(false);  // 登录失败后禁用推荐课程按钮
            }
        } else if (e.getSource() == chushihua) {
            new f2(u1, this);  // 点击初始化按钮，打开 f2 窗口
        } else if (e.getSource() == chakan) {
            new f3(u1, this);  // 点击查看数值按钮，打开 f3 窗口
        } else if (e.getSource() == xuanke) {
            f4Window=new f4(u1, this);  // 点击选课按钮，打开 f4 窗口
        } else if (e.getSource() == yixuekecheng) {
            new f5(u1, this);  // 点击已学课程按钮，打开 f5 窗口
        } else if (e.getSource() == tuijian) {
            new f6(f4Window,u1, this);  // 点击推荐课程按钮，打开 f6 窗口
        }

        setVisible(true);
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}

    public void windowActivated(WindowEvent e) {}

    public void windowDeactivated(WindowEvent e) {}

    public void windowClosed(WindowEvent e) {}

    public void windowIconified(WindowEvent e) {}

    public void windowDeiconified(WindowEvent e) {}
}

// 初始化身体数据窗口
class f2 extends Frame {
    user u;
    TextField[] abFields = new TextField[10];  // 用于存储输入的字段
    Button submitButton;

    public f2(user u, Frame parentFrame) {
        super("初始化身体数据");
        this.u = u;

        setSize(400, 500);
        setLayout(new GridLayout(12, 2));

        // 创建并添加TextField组件
        for (int i = 0; i < 10; i++) {
            add(new Label("ab" + (i + 1) + ":"));
            abFields[i] = new TextField(10);
            add(abFields[i]);
        }

        // 创建提交按钮
        submitButton = new Button("提交");
        add(submitButton);

        // 按钮事件处理
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 获取输入的值并保存到 user 类
                u.setBody(
                        abFields[0].getText(), abFields[1].getText(), abFields[2].getText(),
                        abFields[3].getText(), abFields[4].getText(), abFields[5].getText(),
                        abFields[6].getText(), abFields[7].getText(), abFields[8].getText(),
                        abFields[9].getText()
                );
                // 显示成功信息
                System.out.println("身体数据初始化成功！");
                for (int i = 0; i < 10; i++) {
                    System.out.println("ab" + (i + 1) + ": " + abFields[i].getText());
                }
                // 关闭当前窗口
                dispose();
            }
        });

        // 设置窗口位置在登录窗口的左边
        Point parentLocation = parentFrame.getLocation();
        setLocation(parentLocation.x - getWidth(), parentLocation.y); // 左边

        // 监听窗口关闭事件
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();  // 关闭当前窗口
            }
        });

        // 显示窗口
        setVisible(true);
    }
}

// 查看身体数据窗口
class f3 extends Frame {
    user u;

    public f3(user u, Frame parentFrame) {
        super("查看数值");
        this.u = u;

        setSize(400, 400);
        setLayout(new GridLayout(11, 2));

        // 获取ab1到ab10的值
        String[] values = u.getBody();

        // 创建并添加Label组件来显示ab1到ab10的值
        for (int i = 0; i < 10; i++) {
            add(new Label("ab" + (i + 1) + ":"));
            add(new Label(values[i] == null ? "未设置" : values[i]));  // 如果值为null，显示“未设置”
        }

        // 设置窗口位置在登录窗口的右边
        Point parentLocation = parentFrame.getLocation();
        setLocation(parentLocation.x + parentFrame.getWidth(), parentLocation.y); // 右边

        // 监听窗口关闭事件
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();  // 关闭当前窗口
            }
        });

        // 显示窗口
        setVisible(true);
    }
}

class f4 extends Frame {
    user u;
    CourseBinaryTree courseTree; // 使用二叉树存储课程信息

    public f4(user u, Frame parentFrame) {
        super("选课系统");
        this.u = u;

        courseTree = new CourseBinaryTree();

        // 初始化课程信息并插入到二叉树中
        courseTree.insertCourse("课程 1", (int)(Math.random() * 5) + 1, "https://www.bodybuilding.com");
        courseTree.insertCourse("课程 2", (int)(Math.random() * 5) + 1, "https://www.myfitnesspal.com");
        courseTree.insertCourse("课程 3", (int)(Math.random() * 5) + 1, "https://www.fitnessblender.com");
        courseTree.insertCourse("课程 4", (int)(Math.random() * 5) + 1, "https://www.fitbit.com");
        courseTree.insertCourse("课程 5", (int)(Math.random() * 5) + 1, "https://www.menshealth.com");
        courseTree.insertCourse("课程 6", (int)(Math.random() * 5) + 1, "https://www.womenshealthmag.com");
        courseTree.insertCourse("课程 7", (int)(Math.random() * 5) + 1, "https://www.trainerize.com");
        courseTree.insertCourse("课程 8", (int)(Math.random() * 5) + 1, "https://www.jefit.com");
        courseTree.insertCourse("课程 9", (int)(Math.random() * 5) + 1, "https://athleanx.com");
        courseTree.insertCourse("课程 10", (int)(Math.random() * 5) + 1, "https://www.nike.com/ntc-app");

        setSize(800, 500);
        setLayout(new GridLayout(10, 4));

        // 显示课程信息
        for (int i = 1; i <= 10; i++) {
            // 查找课程信息
            CourseNode course = courseTree.findCourse(i);  

            if (course != null) {
                add(new Label(course.courseName + ":"));
                add(new Label("分数：" + course.courseScore));
                add(new Label("网址：" + course.courseUrl));

                Button selectButton = new Button("选择课程 " + i);
                
                // 这里将 i 传递给一个 final 变量
                final int courseIndex = i;  // 将 i 赋给一个 final 变量

                selectButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        u.addSelectedCourse(course.courseName);  // 使用 course 对象
                        u.addScoreToBody(courseIndex, course.courseScore);  // 使用 final courseIndex
                        System.out.println("已选择 " + course.courseName);
                    }
                });
                add(selectButton);
            }
        }

        // 设置窗口位置在登录窗口的左下角
        Point parentLocation = parentFrame.getLocation();
        setLocation(parentLocation.x - 300, parentLocation.y + parentFrame.getHeight() - getHeight() + 500); // 左下角

        // 监听窗口关闭事件
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();  // 关闭当前窗口
            }
        });

        // 显示窗口
        setVisible(true);
    }
    public int find(int i)
    {
        return courseTree.findCourse(i).courseScore;
    }
}



// 已学课程窗口
class f5 extends Frame 
{
    user u;

    public f5(user u, Frame parentFrame)
    {
        super("已学课程");
        this.u = u;

        setSize(400, 400);
        setLayout(new GridLayout(u.getSelectedCourses().size() + 1, 2));

        // 获取已选课程并显示
        ArrayList<String> selectedCourses = u.getSelectedCourses();
        if (selectedCourses.isEmpty()) {
            add(new Label("您没有选择任何课程"));
        } else {
            try
            {
                File outf=new File("D:\\Java\\"+u.name+".txt");
                FileWriter out=new FileWriter(outf);
                BufferedWriter bout=new BufferedWriter(out);
                for (String course : selectedCourses) {
                    add(new Label(course));  // 显示已选课程
                    bout.write(course, 0, course.length());
                    bout.newLine();
                }
                bout.close();
            }
            catch(FileNotFoundException e){ System.out.println("文件没找到！\n"+e); }
            catch(IOException e){ System.out.println("File read error！\n"+e); }
        }

        // 设置窗口位置在登录窗口的右下角
        Point parentLocation = parentFrame.getLocation();
        setLocation(parentLocation.x + parentFrame.getWidth() - getWidth(), parentLocation.y + parentFrame.getHeight() - getHeight() + 400); // 右下角

        // 监听窗口关闭事件
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();  // 关闭当前窗口
            }
        });

        // 显示窗口
        setVisible(true);
    }
}

//推荐课程窗口
class f6 extends Frame {
    user u;
    f4 f4w;
    public f6(f4 f4w,user u, Frame parentFrame) {
        super("推荐课程");
        this.u = u;
        this.f4w=f4w;

        setSize(400, 300);
        setLayout(new GridLayout(6, 2));  // 显示课程名称和推荐理由

        
        // 显示推荐课程
        add(new Label("身体数据"+u.getLowestAbCourse()+"较为薄弱"));  // 课程名称
        add(new Label("推荐课程："+u.getLowestAbCourse()+"  分数为："+f4w.find(u.getLowestAbCourse())));  // 推荐理由
        

        // 设置窗口位置在登录窗口的正下方
        Point parentLocation = parentFrame.getLocation();
        setLocation(parentLocation.x, parentLocation.y + parentFrame.getHeight());  // 正下方

        // 监听窗口关闭事件
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();  // 关闭当前窗口
            }
        });

        // 显示窗口
        setVisible(true);
    }
    
    
}

// 主类，启动程序
class test {
    public static void main(String[] arr) 
    {
        String[] s=new String[2];
        File inf=new File("D:\\Java\\a.txt");
        try
        {
            int i=0;
            FileReader in=new FileReader(inf);
            BufferedReader bin=new BufferedReader(in);
            while(i<2)
            {
                s[i]=bin.readLine().trim();
                System.out.println(s[i]);
                i++;
            }
            bin.close();
        }
        catch(FileNotFoundException e){ System.out.println("文件没找到！\n"+e); }
        catch(IOException e){ System.out.println("File read error！\n"+e); }
        user u1 = new user(s[0], s[1]);
        frame1 f1 = new frame1(u1);
    }
}
