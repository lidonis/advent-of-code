.DEFAULT_GOAL := generate-day-files

YEAR=`date +'%Y'`
DAY=`date +'%-d'`
NAME:= Template
LOWERCASE_NAME := $(shell echo $(NAME) | awk '{print tolower(substr($$0,1,1)) substr($$0,2)}')
YEAR_PACKAGE_DIR := /kotlin/fr/lidonis/adventofcode/y$(YEAR)
YEAR_FILE := src/main/${YEAR_PACKAGE_DIR}/AdventOfCode$(YEAR).kt
DAY_PACKAGE_DIR := $(YEAR_PACKAGE_DIR)/day$(DAY)
DAY_FILE := src/main/${DAY_PACKAGE_DIR}/Day$(DAY).kt
TEMPLATE_FILE := src/main/${DAY_PACKAGE_DIR}/${NAME}.kt
TEST_TEMPLATE_FILE := src/test/${DAY_PACKAGE_DIR}/${NAME}Test.kt
TARGET_FILES := $(DAY_FILE) $(TEMPLATE_FILE) $(TEST_TEMPLATE_FILE)

.PHONY: generate-year-files
generate-year-files:
	@echo "Generating year files..."
	@echo "Generating Day$(DAY).kt"
	@echo "Replacing variables in files"
	@mkdir -p src/main/${YEAR_PACKAGE_DIR}
	@cp template/year/AdventOfCodeX.kt.mustache $(YEAR_FILE)
	@sed -i '' "s|{{year}}|${YEAR}|g" ${YEAR_FILE}
	@echo "Done generating year files."

.PHONY: generate-day-files
generate-day-files:
	@echo "Generating day files..."
	@touch src/main/resources/input/y$(YEAR)/day$(DAY).txt
	@echo "Generating Day$(DAY).kt"
	@mkdir -p src/main/${DAY_PACKAGE_DIR}
	@cp template/day/DayX.kt.mustache $(DAY_FILE)
	@echo "Generating ${NAME}.kt"
	@cp template/day/Template.kt.mustache $(TEMPLATE_FILE)
	@echo "Generating TemplateTest.kt"
	@mkdir -p src/test/${DAY_PACKAGE_DIR}
	@cp template/day/TemplateTest.kt.mustache $(TEST_TEMPLATE_FILE)
	@echo "Replacing variables in files"
	@sed -i '' "s|{{year}}|${YEAR}|g" ${TARGET_FILES}
	@sed -i '' "s|{{day}}|${DAY}|g" ${TARGET_FILES}
	@sed -i '' "s|{{name}}|${NAME}|g" ${TARGET_FILES}
	@sed -i '' "s|{{lname}}|${LOWERCASE_NAME}|g" ${TARGET_FILES}
	@echo "Done generating day files."

