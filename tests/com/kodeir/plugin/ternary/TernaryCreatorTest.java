package com.kodeir.plugin.ternary;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

/**
 * Created by Sergei "Kodeir" Riabinin on 13/03/17.
 */
public class TernaryCreatorTest extends LightCodeInsightFixtureTestCase{
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected String getTestDataPath() {
        return "testData/";
    }

    public void testInsertionToBlank(){
        myFixture.configureByFile("EmptyClass.java");
        myFixture.complete(CompletionType.BASIC, 1);
        myFixture.performEditorAction("TernarPlugin.CreateTernary");
        myFixture.checkResultByFile("ternarySignature.txt");
    }
}
