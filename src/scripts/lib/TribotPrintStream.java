package scripts.lib;

import org.tribot.script.Script;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by mike on 8/10/15.
 */
public class TribotPrintStream extends PrintStream {
    public TribotPrintStream( final Script script ) {
        super( new OutputStream() {
            char[] buffer = new char[ 80 ];
            int current = 0;

            @Override
            public void write( int b ) throws IOException {
                char current_char = ( char ) b;

                if( b == 10 || current >= 80 ) { // flush
                    String line = new String( buffer, 0, current );

                    script.println( line );
                    for( int i = 0; i < buffer.length; i++ ) buffer[ i ] = 0;
                    current = 0;
                    return;
                }

                buffer[ current++ ] = current_char;
            }
        } );
    }
}
