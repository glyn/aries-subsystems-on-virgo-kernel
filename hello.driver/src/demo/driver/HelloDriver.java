package demo.driver;

import java.io.File;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.subsystem.Subsystem;

public class HelloDriver implements BundleActivator {

	private ServiceReference<Subsystem> sr;
	private Subsystem rootSubsystem;

	@Override
	public void start(BundleContext bc) throws Exception {
		this.sr = (ServiceReference<Subsystem>) bc.getServiceReference(Subsystem.class.getName());
		this.rootSubsystem = bc.getService(this.sr);
		this.rootSubsystem.install(new File("hello.esa").toURI().toString());
	}

	@Override
	public void stop(BundleContext bc) throws Exception {
		if (this.sr != null) {
			bc.ungetService(this.sr);
			this.sr = null;
		}
	}

}
