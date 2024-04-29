package be.pxl.helpdesk.config;

import be.pxl.helpdesk.domain.Ticket;
import be.pxl.helpdesk.domain.User;
import be.pxl.helpdesk.domain.UserRole;
import be.pxl.helpdesk.repository.TicketRepository;
import be.pxl.helpdesk.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ImportData implements CommandLineRunner {

	private final Logger LOGGER = LogManager.getLogger(ImportData.class);

	private final UserRepository userRepository;
	private final TicketRepository ticketRepository;


	public ImportData(UserRepository userRepository, TicketRepository ticketRepository) {
		this.userRepository = userRepository;
		this.ticketRepository = ticketRepository;
	}

	@Override
	public void run(String... args) {
		LOGGER.info("Importing testdata...");

		User spountney0 = userRepository.save(new User("spountney0", "820179@student.pxl.be"));
		userRepository.save(new User("edudleston1","558008@student.pxl.be"));
		userRepository.save(new User("iimesson2","875878@pxl.be"));
		userRepository.save(new User("vcristofaro3","718725@student.pxl.be"));
		userRepository.save(new User("dspira4","378874@student.pxl.be"));
		userRepository.save(new User("pawson5","235340@pxl.be", UserRole.ADMIN));
		userRepository.save(new User("cmaccoughan6","985847@student.pxl.be"));
		userRepository.save(new User("jwhittlesey7","589822@student.pxl.be"));
		User bpitt8 = userRepository.save(new User("bpitt8", "753042@student.pxl.be"));
		userRepository.save(new User("gguppey9","571121@student.pxl.be"));
		userRepository.save(new User("emcgrilla","293121@student.pxl.be"));
		userRepository.save(new User("odreinib","506341@student.pxl.be"));
		userRepository.save(new User("kmillmoec","775466@student.pxl.be"));
		userRepository.save(new User("gkropd","861568@pxl.be", UserRole.ADMIN));
		userRepository.save(new User("pmaccroarye","370163@student.pxl.be"));
		userRepository.save(new User("scavetf","729725@student.pxl.be"));
		userRepository.save(new User("lgiacobonig","248976@student.pxl.be"));
		userRepository.save(new User("acoxonh","887902@student.pxl.be"));
		userRepository.save(new User("mcraggi","928156@pxl.be"));
		userRepository.save(new User("pbatripj","567708@student.pxl.be"));
		userRepository.save(new User("mdonsonk","924681@student.pxl.be"));
		userRepository.save(new User("veasuml","400632@pxl.be", UserRole.ADMIN));
		User tbancroftm = new User("tbancroftm", "417959@student.pxl.be");
		tbancroftm.setLocked(true);
		userRepository.save(tbancroftm);
		userRepository.save(new User("zjakubskin","636095@student.pxl.be"));
		userRepository.save(new User("wloramo","714839"));

		ticketRepository.save(new Ticket(spountney0, "Printer on fire", "My printer is on fire. The colours are beautiful."));
		ticketRepository.save(new Ticket(bpitt8, "Internet switched off", "Can you please turn on the internet."));

	}
}

