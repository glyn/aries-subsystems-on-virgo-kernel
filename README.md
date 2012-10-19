This is an experiment of running Aries Subsystems on the Virgo kernel.

To try it out:

1. git clone git://github.com/glyn/aries-subsystems-on-virgo-kernel.git
2. cd aries-subsystems-on-virgo-kernel/virgo-kernel
3. bin/startup.sh -clean [or, on Windows, bin\startup.bat -clean]
4. [from another terminal] cp ../hello.driver.jar pickup/. [or, on Windows, copy ..\hello.driver.jar pickup\\.]
5. Browse http://localhost:8080/admin/content/artifacts [user admin, password springsource]
6. Click the Region button to see composite and application regions plus their contents.

Note: the demo runs both Gemini Blueprint and Aries Blueprint in the user region which is not normally recommended.