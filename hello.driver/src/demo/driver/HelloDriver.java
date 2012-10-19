
package demo.driver;

import java.io.File;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.subsystem.Subsystem;

public class HelloDriver {

    private ServiceReference<Subsystem> sr;

    private final BundleContext bc;

    private final Subsystem rootSubsystem;

    private Subsystem helloApplicationSubsystem;

    private Subsystem helloFeatureSubsystem;

    private Subsystem helloCompositeSubsystem;

    private Subsystem helloNestedCompositeSubsystem;

    public HelloDriver(BundleContext bc, Subsystem rootSubsystem) {
        this.bc = bc;
        this.rootSubsystem = rootSubsystem;
    }
    
    public void create() throws Exception {
        this.helloApplicationSubsystem = deploySubsystem("hello.application.esa");
        this.helloFeatureSubsystem = deploySubsystem("hello.feature.esa");
        this.helloCompositeSubsystem = deploySubsystem("hello.composite.esa");
        this.helloNestedCompositeSubsystem = deploySubsystem("hello.nested.composite.esa");
    }

    private Subsystem deploySubsystem(String subsystemDirectoryName) {
        Subsystem helloSubsystem = this.rootSubsystem.install(new File("../" + subsystemDirectoryName).toURI().toString());
        helloSubsystem.start();
        return helloSubsystem;
    }

    public void destroy() throws Exception {
        if (this.helloNestedCompositeSubsystem != null) {
            this.helloNestedCompositeSubsystem.uninstall();
            this.helloNestedCompositeSubsystem = null;
        }
        if (this.helloCompositeSubsystem != null) {
            this.helloCompositeSubsystem.uninstall();
            this.helloCompositeSubsystem = null;
        }
        if (this.helloFeatureSubsystem != null) {
            this.helloFeatureSubsystem.uninstall();
            this.helloFeatureSubsystem = null;
        }
        if (this.helloApplicationSubsystem != null) {
            this.helloApplicationSubsystem.uninstall();
            this.helloApplicationSubsystem = null;
        }
        if (this.sr != null) {
            this.bc.ungetService(this.sr);
            this.sr = null;
        }
    }

}
