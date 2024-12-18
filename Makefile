.DEFAULT_GOAL := build

YEAR=`date +'%Y'`
DAY=`date +'%-d'`
NAME:= Template
LOWERCASE_NAME := $(shell echo $(NAME) | awk '{print tolower(substr($$0,1,1)) substr($$0,2)}')
YEAR_PACKAGE_DIR := /kotlin/fr/lidonis/adventofcode/y$(YEAR)
YEAR_FILE := aoc$(YEAR)/src/commonMain/${YEAR_PACKAGE_DIR}/AdventOfCode$(YEAR).kt
DAY_PACKAGE_DIR := $(YEAR_PACKAGE_DIR)/day$(DAY)
DAY_FILE := aoc$(YEAR)/src/commonMain${DAY_PACKAGE_DIR}/Day$(DAY).kt
TEMPLATE_FILE := aoc$(YEAR)/src/commonMain${DAY_PACKAGE_DIR}/${NAME}.kt
TEST_TEMPLATE_FILE := aoc$(YEAR)/src/jvmTest${DAY_PACKAGE_DIR}/${NAME}Test.kt
TARGET_FILES := $(DAY_FILE) $(TEMPLATE_FILE) $(TEST_TEMPLATE_FILE)

.PHONY: build
build:
	@echo "Building project..."
	@./gradlew build

.PHONY: build-native
build-native:
	@echo "Building native project..."
	@echo "Building tests with graal agent for reflection..."
	@./gradlew -Pagent test
	@echo "Copy reflexion files..."
	@./gradlew metadataCopy --task test --dir src/main/resources/META-INF/native-image
	@echo "Building native image..."
	@./gradlew nativeCompile

.PHONY: run
run:
	@echo "Running project..."
	@./gradlew run --args="-y ${YEAR} -d ${DAY}"

.PHONY: run-native
run-native:
	@echo "Running native project..."
	@./build/native/nativeCompile/advent-of-code -y ${YEAR} -d ${DAY}

.PHONY: generate-year-files
generate-year-files:
	@echo "Generating year files..."
	@echo "Generating $(YEAR) module"
	@mkdir -p aoc$(YEAR)
	@cp template/year/build.gradle.kts.mustache aoc$(YEAR)/build.gradle.kts
	@echo "include(\"aoc$(YEAR)\")" >> settings.gradle.kts
	@echo "Generating Year$(YEAR).kt"
	@mkdir -p aoc$(YEAR)/src/commonMain/${YEAR_PACKAGE_DIR}
	@cp template/year/AdventOfCodeX.kt.mustache $(YEAR_FILE)
	@echo "Replacing variables in files"
	@sed -i '' "s|{{year}}|${YEAR}|g" ${YEAR_FILE}
	@echo "Done generating year files."

generate-day-files:
	@echo "Generating day files..."
	@mkdir -p aoc$(YEAR)/src/commonMain/${DAY_PACKAGE_DIR}
	@cp template/day/DayX.kt.mustache $(DAY_FILE)
	@echo "Generating ${NAME}.kt"
	@cp template/day/Template.kt.mustache $(TEMPLATE_FILE)
	@echo "Generating ${NAME}Test.kt"
	@mkdir -p aoc$(YEAR)/src/jvmTest/${DAY_PACKAGE_DIR}
	@cp template/day/TemplateTest.kt.mustache $(TEST_TEMPLATE_FILE)
	@echo "Replacing variables in files"
	@sed -i '' "s|{{year}}|${YEAR}|g" ${TARGET_FILES}
	@sed -i '' "s|{{day}}|${DAY}|g" ${TARGET_FILES}
	@sed -i '' "s|{{name}}|${NAME}|g" ${TARGET_FILES}
	@sed -i '' "s|{{lname}}|${LOWERCASE_NAME}|g" ${TARGET_FILES}
