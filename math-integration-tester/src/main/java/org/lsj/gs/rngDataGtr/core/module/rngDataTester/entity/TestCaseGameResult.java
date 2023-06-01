package org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity;

import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;

// 測項遊戲結果
public class TestCaseGameResult {
    private final BoardGtrResult boardGtrResult; // 局結果
    private final TestCaseResult testCaseResult; // 檢測結果

    public TestCaseGameResult(BoardGtrResult boardGtrResult, TestCaseResult testCaseResult) {
        this.boardGtrResult = boardGtrResult;
        this.testCaseResult = testCaseResult;
    }

    public BoardGtrResult getBoardGtrResult() {
        return boardGtrResult;
    }

    public TestCaseResult getTestCaseResult() {
        return testCaseResult;
    }
}
