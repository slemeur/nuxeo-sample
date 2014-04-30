package org.nuxeo.sample;

import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.collectors.DocumentModelCollector;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentRef;
import org.nuxeo.sample.MyOperation;

 /**
 * Sample Operation
 *
 */
@Operation(id = MyOperation.ID, category = Constants.CAT_DOCUMENT, label = "My Sample operationr", description = " ")
public class MyOperation {

    public static final String ID = "myOperationId";

    @Context
    CoreSession coreSession;
    
    @OperationMethod(collector=DocumentModelCollector.class)
    public DocumentModel run(DocumentRef doc) throws Exception {
        coreSession.setLock(doc);
        return coreSession.getDocument(doc);
    }

    @OperationMethod(collector=DocumentModelCollector.class)
    public DocumentModel run(DocumentModel doc) throws Exception {
        coreSession.setLock(doc.getRef());
        return coreSession.getDocument(doc.getRef());
    }

}
