package com.kodeir.plugin.ternary;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.testFramework.fixtures.CodeInsightTestFixture;
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

    public void testCompletion(){
        myFixture.configureByFile("TestClass.java");
        myFixture.complete(CompletionType.BASIC, 1);
        myFixture.performEditorAction("TernarPlugin.CreateTernary");
    }
}
