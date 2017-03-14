import groovy.transform.CompileStatic
import groovy.transform.SelfType
import org.testng.collections.Lists

class Component {
    void methodInComponent(){
        println ("Calling in component")
    }
}

@CompileStatic
@SelfType(Component)
trait ComponentDecorator {
     void logAndCall() {
         println "Calling method in component"
         methodInComponent()
     }
     // other useful methods
 }
class DecoratedComponent extends Component implements ComponentDecorator {}

class Test {
    public static void main(String[] args) {
        System.out.println "haha"
        def logs = []
        List<String> l = Lists.newArrayList("1", "2", "3");
        logs.addAll(l)
        List<String> l2 = Lists.newArrayList("4", "5", "6");
        logs.addAll(l2)
        println logs
        new DecoratedComponent().logAndCall()
    }
}
