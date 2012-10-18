
package demo.driver;

import java.io.File;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.subsystem.Subsystem;

public class HelloDriver implements BundleActivator {

    private ServiceReference<Subsystem> sr;

    private Subsystem rootSubsystem;

    private Subsystem helloApplicationSubsystem;
    
    private Subsystem helloFeatureSubsystem;

    @Override
    public void start(BundleContext bc) throws Exception {
        this.sr = bc.getServiceReference(Subsystem.class);
        this.rootSubsystem = bc.getService(this.sr);
        this.helloApplicationSubsystem = deploySubsystem("hello.application.esa");
        this.helloFeatureSubsystem = deploySubsystem("hello.feature.esa");
    }

    private Subsystem deploySubsystem(String subsystemDirectoryName) {
        Subsystem helloSubsystem = this.rootSubsystem.install(new File(subsystemDirectoryName).toURI().toString());
        helloSubsystem.start();
        return helloSubsystem;
    }

    @Override
    public void stop(BundleContext bc) throws Exception {
        if (this.helloApplicationSubsystem != null) {
            this.helloApplicationSubsystem.uninstall();
            this.helloApplicationSubsystem = null;
        }
        if (this.helloFeatureSubsystem != null) {
            this.helloFeatureSubsystem.uninstall();
            this.helloFeatureSubsystem = null;
        }
        if (this.sr != null) {
            bc.ungetService(this.sr);
            this.sr = null;
        }
    }

}
