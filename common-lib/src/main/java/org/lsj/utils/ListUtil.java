package org.lsj.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// 陣列工具包
public class ListUtil {

    // 不考慮順序，陣列是否相同[考慮重複]
    public static boolean isSameListWithoutOrder(List<Integer> listA, List<Integer> listB) {
        // 檢查陣列大小
        if (listA.size() != listB.size()) {
            return false;
        }

        // 排序陣列
        List<Integer> sortA = listA.stream().sorted().collect(Collectors.toList());
        List<Integer> sortB = listB.stream().sorted().collect(Collectors.toList());

        // 比較陣列是否一致
        return sortA.equals(sortB);
    }

    // 判斷是否為目標陣列的子陣列
    public static boolean isSubList(List<Integer> mainList, List<Integer> subList) {
        if (subList.size() > mainList.size()) {
            return false;
        }

        return subList.stream().allMatch(element -> mainList.contains(element));
    }


    //* 1D 陣列操作 *//
    // 將負數移到陣列最上方
    public static List<Integer> transNegativeNumberToArrayFirst(List<Integer> targetArray) {
        // 1. 宣告空間
        List<Integer> result = new ArrayList<>();

        // 2. 陣列先放負數
        for (int index = 0; index < targetArray.size(); index++) {
            if (targetArray.get(index) < 0) {
                result.add(-1);
            }
        }

        // 3. 依序放入正數
        for (int index = 0; index < targetArray.size(); index++) {
            if (targetArray.get(index) >= 0) {
                result.add(targetArray.get(index));
            }
        }

        // 4. 回傳
        return result;
    }

    // 複製 1D int 陣列
    public static List<Integer> copy1DimensionIntegerList(List<Integer> targetList) {
        List<Integer> result = new ArrayList<>();

        targetList.stream().forEach(x -> result.add(x));

        return result;
    }


    //* 2D 陣列操作 *//
    // 建立2D boolean 陣列 指定單一內容(true,false)
    public static <T> List<List<Boolean>> create2DimensionBooleanList(List<List<T>> targetList, Boolean targetContent) {
        List<List<Boolean>> result = new ArrayList<>();

        for (List<T> columnList : targetList) {
            List<Boolean> listPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < columnList.size(); rowIndex++) {
                listPerColumn.add(targetContent);
            }

            result.add(listPerColumn);
        }

        return result;
    }

    // 複製2D boolean 陣列
    public static List<List<Boolean>> copy2DimensionBooleanList(List<List<Boolean>> targetList) {
        List<List<Boolean>> result = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < targetList.size(); columnIndex++) {
            List<Boolean> listPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < targetList.get(columnIndex).size(); rowIndex++) {
                listPerColumn.add(targetList.get(columnIndex).get(rowIndex));
            }

            result.add(listPerColumn);
        }

        return result;
    }

    // 複製2D int 陣列
    public static List<List<Integer>> copy2DimensionIntegerList(List<List<Integer>> targetList) {
        List<List<Integer>> result = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < targetList.size(); columnIndex++) {
            List<Integer> listPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < targetList.get(columnIndex).size(); rowIndex++) {
                listPerColumn.add(targetList.get(columnIndex).get(rowIndex));
            }

            result.add(listPerColumn);
        }

        return result;
    }

    // 顛倒陣列左右順序
    public static List<List<Integer>> inverse2DimensionIntegerList(List<List<Integer>> targetList) {
        List<List<Integer>> result = new ArrayList<>();

        for (int columnIndex = targetList.size() - 1; columnIndex >= 0; columnIndex--) {
            List<Integer> listPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < targetList.get(columnIndex).size(); rowIndex++) {
                listPerColumn.add(targetList.get(columnIndex).get(rowIndex));
            }

            result.add(listPerColumn);
        }

        return result;
    }

    // 顛倒陣列左右順序
    public static List<List<Boolean>> inverse2DimensionBooleanList(List<List<Boolean>> targetList) {
        List<List<Boolean>> result = new ArrayList<>();

        for (int columnIndex = targetList.size() - 1; columnIndex >= 0; columnIndex--) {
            List<Boolean> listPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < targetList.get(columnIndex).size(); rowIndex++) {
                listPerColumn.add(targetList.get(columnIndex).get(rowIndex));
            }

            result.add(listPerColumn);
        }

        return result;
    }

    // 聯集相同大小 2D boolean 陣列
    public static List<List<Boolean>> unionSameSize2DimensionBooleanList(List<List<Boolean>> listA, List<List<Boolean>> listB) {
        // 1. size 不同回傳空集合
        if (listA.size() != listB.size()) {
            return Collections.emptyList();
        }

        List<List<Boolean>> result = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < listA.size(); columnIndex++) {
            List<Boolean> resultPerColumn = new ArrayList<>();

            // 2. size 不同回傳空集合
            if (listA.get(columnIndex).size() != listB.get(columnIndex).size()) {
                return Collections.emptyList();
            }

            for (int rowIndex = 0; rowIndex < listA.get(columnIndex).size(); rowIndex++) {
                // 3. 任一個為真，就為真
                if (listA.get(columnIndex).get(rowIndex) == true || listB.get(columnIndex).get(rowIndex) == true) {
                    resultPerColumn.add(true);
                }else {
                    resultPerColumn.add(false);
                }
            }

            // 4. 添加該欄結果
            result.add(resultPerColumn);
        }

        // 5. 回傳
        return result;
    }
}
