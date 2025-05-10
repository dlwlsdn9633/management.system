package com.service.management.system.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.time.LocalDate;
import java.util.Optional;

public class ExcelUtil {
    /**
     * @param cell Excel 셀
     * @param <T> 반환할 타입
     * @return Optional<T> 반환된 값, 타입에 맞지 않으면 Optional.empty()
     * */
    public static <T> Optional<T> getValue(Cell cell, Class<T> targetType) {
        if (cell == null) {
            return Optional.empty();
        }
        try {
            switch (cell.getCellType()) {
                case STRING:
                    if (targetType.isAssignableFrom(String.class)) {
                        return Optional.of(targetType.cast(cell.getStringCellValue()));
                    }
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        if (targetType.isAssignableFrom(java.util.Date.class)) {
                            return Optional.of(targetType.cast(cell.getDateCellValue()));
                        } else if (targetType.isAssignableFrom(LocalDate.class)) {
                            return Optional.of(targetType.cast(cell.getLocalDateTimeCellValue().toLocalDate()));
                        }
                    } else {
                        if (targetType.isAssignableFrom(Double.class)) {
                            return Optional.of(targetType.cast(cell.getNumericCellValue()));
                        } else if (targetType.isAssignableFrom(Integer.class)) {
                            return Optional.of(targetType.cast((int) cell.getNumericCellValue()));
                        }
                    }
                case BOOLEAN:
                    if (targetType.isAssignableFrom(Boolean.class)) {
                        return Optional.of(targetType.cast(cell.getBooleanCellValue()));
                    }
                    break;
                case FORMULA:
                    if (targetType.isAssignableFrom(String.class)) {
                        return Optional.of(targetType.cast(cell.getCellFormula()));
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
