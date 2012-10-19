
package demo.driver;

import java.io.File;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.subsystem.Subsystem;

public class HelloDriver {

    private ServiceReference<Subsystem> sr;

    private Subsystem rootSubsystem;

    private Subsystem helloApplicationSubsystem;
    
    private Subsystem helloFeatureSubsystem;

    private Subsystem helloCompositeSubsystem;

    private Subsystem helloNestedCompositeSubsystem;

    public void activate(ComponentContext componentContext) throws Exception {
        BundleContext bc = componentContext.getBundleContext();
        this.sr = bc.getServiceReference(Subsystem.class);
        this.rootSubsystem = bc.getService(this.sr);
        this.helloApplicationSubsystem = deploySubsystem("hello.application.esa");
        this.helloFeatureSubsystem = deploySubsystem("hello.feature.esa");
        this.helloCompositeSubsystem = deploySubsystem("hello.composite.esa");
        this.helloNestedCompositeSubsystem = deploySubsystem("hello.nested.composite.esa");
    }

    private Subsystem deploySubsystem(String subsystemDirectoryName) {
        Subsystem helloSubsystem = this.rootSubsystem.install(new File(subsystemDirectoryName).toURI().toString());
        helloSubsystem.start();
        return helloSubsystem;
    }

    public void deactivate(ComponentContext componentContext) throws Exception {
        BundleContext bc = componentContext.getBundleContext();
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
            bc.ungetService(this.sr);
            this.sr = null;
        }
    }

}
