/**readme:
 * eclipse->Run as->Groovy Script / Groovy Console.
 */

@Grab(group='org.codehaus.gpars', module='gpars', version='1.2.1')

/**
 * http://gpars.codehaus.org/Groovy+Fast+Track
 */
import groovyx.gpars.GParsPool

GParsPool.withPool {
	def animals = ['dog', 'ant', 'cat', 'whale']
	println(animals.anyParallel {it ==~ /ant/} ? 'Found an ant' : 'No ants found')
	println(animals.everyParallel {it.contains('a')} ? 'All animals contain a' : 'Some animals can live without an a')
}


import groovyx.gpars.actor.DynamicDispatchActor
import org.codehaus.groovy.runtime.NullObject

final class MyActor extends DynamicDispatchActor {
	private int counter = 0

	void onMessage(String message) {
		counter += message.size()
		println 'Received string'
	}

	void onMessage(Integer message) {
		counter += message
		println 'Received integer'
	}

	void onMessage(Object message) {
		counter += 1
		println 'Received object'
	}

	void onMessage(NullObject message) {
		println 'Received a null object. Sending back the current counter value.'
		reply counter
	}
}

final def actor = new MyActor()
actor.start()
actor.send 1
actor << 2
actor 20
actor 'Hello'
println actor.sendAndWait(null)

/**
 * http://gpars.org/1.2.1/guide/guide/single.html
 */
import static groovyx.gpars.actor.Actors.actor
/**
 * A demo showing two cooperating actors. The decryptor decrypts received messages
 * and replies them back.  The console actor sends a message to decrypt, prints out
 * the reply and terminates both actors.  The main thread waits on both actors to
 * finish using the join() method to prevent premature exit, since both actors use
 * the default actor group, which uses a daemon thread pool.
 * @author Dierk Koenig, Vaclav Pech
 */

def decryptor = actor {
	loop {
		react { message ->
			if (message instanceof String) reply message.reverse()
			else stop()
		}
	}
}

def console = actor {
	decryptor.send 'lellarap si yvoorG'
	react {
		println 'Decrypted message: ' + it
		decryptor.send false
	}
}

[decryptor, console]*.join()