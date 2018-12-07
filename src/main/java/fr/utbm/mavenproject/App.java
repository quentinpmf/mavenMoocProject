/* DOC : http://www.javawebtutor.com/articles/hibernate/mvc-appliction-using-servlet-jsp-and-hibernate.php */

package fr.utbm.mavenproject;

import fr.utbm.mavenproject.controller.DefaultLocationController;
import fr.utbm.mavenproject.controller.DefaultCourseController;
import fr.utbm.mavenproject.controller.DefaultCourseSessionController;
import fr.utbm.mavenproject.controller.DefaultClientController;

/**
 *
 * @author 
 */
public class App {

    public static void main(String[] args) 
    {
        DefaultLocationController dlc = new DefaultLocationController();
        dlc.createLocation("belfort");
        
        DefaultCourseController dcc = new DefaultCourseController();
        dcc.createCourse("dqsdqsdqs num2");
        
        DefaultCourseSessionController dcsc = new DefaultCourseSessionController();
        dcsc.createCourseSession("2018-11-24","2018-11-26","20","1","1","test.html");
        
        DefaultClientController dclc = new DefaultClientController();
        dclc.createClient("Bdqsdqsdqsoudinot","dqsdqsdsq","20 rue test","0326547852","quentinpmf@gmail.com","password");
    }
}
