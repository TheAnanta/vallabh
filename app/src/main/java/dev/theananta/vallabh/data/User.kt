package dev.theananta.vallabh.data

data class TimeOfDay(val hour: Int, val minutes: Int)
enum class LevelOfCooking {
    BEGINNER, INTERMEDIATE, ADVANCED
}

enum class Lifestyle {
    SEDENTARY, ACTIVE, HECTIC
}

enum class FlexiNutritionType {
    VEGETARIAN, NON_VEGETARIAN, VEGAN
}

data class FlexiNutritionPreferences(
    val type: FlexiNutritionType,
    val spicePreferences: Float = 0.5f,
    val sweetOrHotPreferences: Float = 0.5f,
    val allergies: Set<String> = setOf(),
    val dietaryRestrictions: Set<String> = setOf()
)

data class HealthMetric(
    val weight: Double,
    val height: Double,
    val waterPercentage: Int?,
    val fatPercentage: Int?,
    val boneMass: Double?,
    val calories: Int?
) {
    val bmi: Double get() = (weight / (height * height))
}

enum class Gender(val value: String) {
    Male("Male"),
    Female("Female"),
    Other("Other")
}

data class UserAddress(
    val name: String,
    val doorNumber: String,
    val apartment: String,
    val city: String,
    val state: String,
    val landmark: String,
    val pincode: String
)

private val defaultFlexiNutritionPreferences =
    FlexiNutritionPreferences(type = FlexiNutritionType.VEGETARIAN)

data class User(
    val name: String,
    val email: String,
    val address: List<UserAddress> = listOf(),
    val age: Int? = null,
    val lifestyle: Lifestyle = Lifestyle.ACTIVE,
    val gender: String = Gender.Male.value,
    val dailyRoutine: List<Pair<String, TimeOfDay>> = listOf(),
    val levelOfCooking: LevelOfCooking = LevelOfCooking.BEGINNER,
    val numberOfDishes: Int = 3,
    val favouriteFoodItems: List<String> = listOf(),
    val flexiNutritionPreferences: FlexiNutritionPreferences = defaultFlexiNutritionPreferences,
    val healthGoals: List<String> = listOf(), // atheletic, grow height, hair care, face care, weight loss,etc
    val healthIssues: List<String> = listOf(),
    val healthMetrics: List<HealthMetric> = listOf()
)
