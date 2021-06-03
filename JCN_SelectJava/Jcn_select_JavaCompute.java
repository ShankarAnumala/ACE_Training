import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbPolicy;
import com.ibm.broker.plugin.MbSQLStatement;
import com.ibm.broker.plugin.MbUserException;


public class Jcn_select_JavaCompute extends MbJavaComputeNode {

	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
		MbOutputTerminal out = getOutputTerminal("out");
//		MbOutputTerminal alt = getOutputTerminal("alternate");

		MbMessage inMessage = inAssembly.getMessage();
		MbMessageAssembly outAssembly = null;
		try {
			// create new message as a copy of the input
			MbMessage outMessage = new MbMessage();
			outAssembly = new MbMessageAssembly(inAssembly, outMessage);
			// ----------------------------------------------------------
			// Add user code below
			MbMessage newMsg = new MbMessage(copyMessageHeaders(inMessage, outMessage));
		    MbMessageAssembly newAssembly = new MbMessageAssembly(inAssembly, newMsg);
		    
//		    MbElement ref = inAssembly.getMessage().getRootElement().getLastChild().getFirstChild();

//		    String table = ref.getFirstElementByPath("table").getValueAsString() /*"DEPARTMENT"*/;
		    
//		    String val = ref.getFirstElementByPath("deptno").getValueAsString();
		    
//		    Character nm = 'T';
		    
//		    String nm = "TEJA";
		    
//		    Integer id = 10;
		    
//		    String table = (String)getUserDefinedAttribute("tname"); -> User defined property (External variable) accessing use
		    
		    //
		    
		    String table = "";
		    
		    MbPolicy myPol = getPolicy("UserDefined", "{UDP}:UDP1");
		    
		    if(myPol != null)
		    {
		    	table = myPol.getPropertyValueAsString("Pname");
		    }

//		    MbSQLStatement state = createSQLStatement( "ORADSN", 
//		                                               "SET OutputRoot.XMLNSC.root.values[] = PASSTHRU('SELECT * FROM " + table + "');" );
		   // success code 
		    MbSQLStatement state1 = createSQLStatement( "ORADSN", "SET OutputRoot.XMLNSC.root.values[] = PASSTHRU('SELECT * FROM " + table + " WHERE ID = 10" + "');" );
		    
//		    MbSQLStatement state1 = createSQLStatement( "ORADSN", "SET OutputRoot.XMLNSC.root.values[] = PASSTHRU('SELECT * FROM " + table + " WHERE id = 14" + "');" );
		    
//		    MbSQLStatement state = createSQLStatement( "ORADSN", "SET OutputRoot.XMLNSC.root.values[] = PASSTHRU('UPDATE " + table + " SET NAME = TEJA WHERE ID = 12" + "');" );
		    
//		    MbSQLStatement state = createSQLStatement( "ORADSN", 
//                    "SET OutputRoot.XMLNSC.root.values[] = SELECT * FROM " + table + " WHERE DEPTNO = " + val + ";" ); // failed to retrieve specific record
		    
//		    MbSQLStatement state = createSQLStatement("ORADSN", "UPDATE Database.TEST AS T SET NAME = 'TEJA' WHERE T.ID = '10';");
		    
//		    MbSQLStatement state1 = createSQLStatement( "NIKHILSOURCE", " UPDATE Database.EMPLOYEE AS D SET COMPANY='INFOSYS' WHERE D.EMPID='11' " );
		    
//		    MbSQLStatement state = createSQLStatement( "ORADSN", " PASSTHRU('UPDATE TEST SET NAME=" + nm + " WHERE ID=" + id + "'); " );
		    
//		    MbSQLStatement state = createSQLStatement( "ORADSN", "UPDATE Database.TEST SET NAME=" + nm + " WHERE ID=" + id + ";");
		    
//		    MbSQLStatement state = createSQLStatement("ORADSN", "INSERT INTO Database.test(id,name) VALUES(" + id +", TEJA)");
		    
//		    MbSQLStatement state1 = createSQLStatement("ORADSN","INSERT INTO Database.SAMPLE (ID1,NAME1,LOC,TOWN) VALUES('99','NIK','hyd','uppal')"); // success code from shyam

		    // success code
		    MbSQLStatement state2 = createSQLStatement("ORADSN","INSERT INTO Database.TEST1 (ID,NAME) VALUES('12','QWERTY')");
		    
//		    MbSQLStatement state1 = createSQLStatement("ORADSN","UPDATE Database.DEPT AS D SET DNAME='DEVELOPMENT' WHERE D.DEPTNO='10'"); // success code from shyam
		    
		    // success code
		    MbSQLStatement state3 = createSQLStatement("ORADSN","UPDATE Database.TEST1 AS D SET NAME='HELLO' WHERE D.ID='12'");
		    
//		    MbSQLStatement state1 = createSQLStatement("ORADSN","DELETE FROM Database.EMP AS E WHERE E.EMPNO=7369"); // success code from shyam
		    
		    // success code
		    MbSQLStatement state4 = createSQLStatement("ORADSN","DELETE FROM Database.TEST1 AS E WHERE E.ID='12'");
		    
		    state1.setThrowExceptionOnDatabaseError(true);
		    state2.setThrowExceptionOnDatabaseError(true);
		    state3.setThrowExceptionOnDatabaseError(true);
		    state4.setThrowExceptionOnDatabaseError(true);
		    
//		    inAssembly = null;

		    state1.select( inAssembly, newAssembly );
//		    state2.select( inAssembly, newAssembly );
//		    state3.select( inAssembly, newAssembly );
//		    state4.select( inAssembly, newAssembly );
		    
		    state2.execute(newAssembly);
		    state3.execute(newAssembly);
		    state4.execute(newAssembly);
		    
//		    state.execute(inAssembly);

		    int sqlCode = state1.getSQLCode();
		    if(sqlCode != 0)
		      {
		        // Do error handling here

		        System.out.println("sqlCode = " + sqlCode);
		        System.out.println("sqlNativeError = " + state1.getSQLNativeError());
		        System.out.println("sqlState = " + state1.getSQLState());
		        System.out.println("sqlErrorText = " + state1.getSQLErrorText());
		      }

		    out.propagate(newAssembly);
			// End of user code
			// ----------------------------------------------------------
		} catch (MbException e) {
			// Re-throw to allow Broker handling of MbException
			throw e;
		} catch (RuntimeException e) {
			// Re-throw to allow Broker handling of RuntimeException
			throw e;
		} catch (Exception e) {
			// Consider replacing Exception with type(s) thrown by user code
			// Example handling ensures all exceptions are re-thrown to be handled in the flow
			throw new MbUserException(this, "evaluate()", "", "", e.toString(),
					null);
		}
		// The following should only be changed
		// if not propagating message to the 'out' terminal
//		out.propagate(outAssembly);

	}
	
	public static MbMessage copyMessageHeaders(MbMessage inMessage, MbMessage outMessage) throws MbException 
	{
			MbElement outRoot = outMessage.getRootElement();
			MbElement header = inMessage.getRootElement().getFirstChild();
			while (header != null && header.getNextSibling()!= null)
			{
				outRoot.addAsLastChild(header.copy());
				header = header.getNextSibling();
			}
			
			return outMessage;
	}

	/**
	 * onPreSetupValidation() is called during the construction of the node
	 * to allow the node configuration to be validated.  Updating the node
	 * configuration or connecting to external resources should be avoided.
	 *
	 * @throws MbException
	 */
	@Override
	public void onPreSetupValidation() throws MbException {
	}

	/**
	 * onSetup() is called during the start of the message flow allowing
	 * configuration to be read/cached, and endpoints to be registered.
	 *
	 * Calling getPolicy() within this method to retrieve a policy links this
	 * node to the policy. If the policy is subsequently redeployed the message
	 * flow will be torn down and reinitialized to it's state prior to the policy
	 * redeploy.
	 *
	 * @throws MbException
	 */
	@Override
	public void onSetup() throws MbException {
	}

	/**
	 * onStart() is called as the message flow is started. The thread pool for
	 * the message flow is running when this method is invoked.
	 *
	 * @throws MbException
	 */
	@Override
	public void onStart() throws MbException {
	}

	/**
	 * onStop() is called as the message flow is stopped. 
	 *
	 * The onStop method is called twice as a message flow is stopped. Initially
	 * with a 'wait' value of false and subsequently with a 'wait' value of true.
	 * Blocking operations should be avoided during the initial call. All thread
	 * pools and external connections should be stopped by the completion of the
	 * second call.
	 *
	 * @throws MbException
	 */
	@Override
	public void onStop(boolean wait) throws MbException {
	}

	/**
	 * onTearDown() is called to allow any cached data to be released and any
	 * endpoints to be deregistered.
	 *
	 * @throws MbException
	 */
	@Override
	public void onTearDown() throws MbException {
	}

}
