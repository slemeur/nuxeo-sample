package org.nuxeo.test.sample;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.sample.MyOperation;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.OperationChain;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.platform.test.PlatformFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import com.google.inject.Inject;

@RunWith(FeaturesRunner.class)
@Features(PlatformFeature.class)
@Deploy({"org.nuxeo.mysample", "org.nuxeo.ecm.automation.core"})
public class MyOperationTest
{

	@Inject
	CoreSession coreSession;

	@Inject
	AutomationService automationService;
 
 
	@Test
	public void testMyOperation() throws Exception {
		DocumentModel doc = coreSession.createDocumentModel("/", "File", "File");
		doc = coreSession.createDocument(doc);
    assertNotNull(doc);
    assertFalse(doc.isLocked());
    OperationContext ctx = new OperationContext(coreSession);
    ctx.setInput(doc);
    String origPrincipal = ctx.getPrincipal().getName();
    OperationChain chain = new OperationChain("testChain");
    chain.add(MyOperation.ID);
    doc = (DocumentModel)automationService.run(ctx, chain);
    assertTrue(doc.isLocked());
	}
}