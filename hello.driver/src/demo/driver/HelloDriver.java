
package demo.driver;

import java.io.File;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.subsystem.Subsystem;

public class HelloDriver implements BundleActivator {

    private ServiceReference<Subsystem> sr;

    private Subsystem rootSubsystem;

    private Subsystem helloSubsystem;

    @Override
    public void start(BundleContext bc) throws Exception {
        this.sr = bc.getServiceReference(Subsystem.class);
        this.rootSubsystem = bc.getService(this.sr);
        this.helloSubsystem = this.rootSubsystem.install(new File("hello.esa").toURI().toString());
        this.helloSubsystem.start();
    }

    @Override
    public void stop(BundleContext bc) throws Exception {
        if (this.helloSubsystem != null) {
            this.helloSubsystem.stop();
            this.helloSubsystem = null;
        }
        if (this.sr != null) {
            bc.ungetService(this.sr);
            this.sr = null;
        }
    }

}
