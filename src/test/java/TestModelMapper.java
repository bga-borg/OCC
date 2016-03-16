import com.bg.thsb.openstack.OSClientWrapper;
import com.bg.thsb.openstack.model.entities.Server;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.openstack4j.openstack.compute.domain.NovaServer;

import java.util.List;

/**
 * TestModelMapper
 *
 * Leirni, hogy mekkora szopas volt a model masolasa
 * eltarolasa, mert nem alkalmas arra az open source komponens
 * hogy a bejovo objektum szerializalasra keruljon.
 */
public class TestModelMapper {
	@Test
	public void testServerCopy() throws Exception {
		//Build
		final List<? extends org.openstack4j.model.compute.Server> list = OSClientWrapper.getOs().compute().servers().list();

		Server server;
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new ServerMap());

		//Operate
		server = modelMapper.map(list.stream().findFirst().get(), Server.class);

		//Check
		System.out.println(server);
	}

	public static class ServerMap extends PropertyMap<NovaServer, Server> {

		@Override
		protected void configure() {
			map().setImageId(source.getImageId());
			map().setFlavorId(source.getFlavorId());
		}
	}
}

