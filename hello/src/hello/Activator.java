package hello;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration<String> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		this.serviceRegistration = context.registerService(String.class,
				"hello service", null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (this.serviceRegistration != null) {
			this.serviceRegistration.unregister();
			this.serviceRegistration = null;
		}
	}

}
