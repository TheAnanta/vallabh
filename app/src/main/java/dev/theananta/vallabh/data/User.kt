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
    val allergies: Set<String>,
    val dietaryRestrictions: Set<String>
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

enum class Gender(val value: String){
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

data class User(
    val name: String,
    val email: String,
    val address: UserAddress?,
    val age: Int,
    val lifestyle: Lifestyle,
    val gender: String,
    val dailyRoutine: List<Pair<String, TimeOfDay>>,
    val levelOfCooking: LevelOfCooking,
    val numberOfDishes: Int,
    val favouriteFoodItems: List<String>,
    val flexiNutritionPreferences: FlexiNutritionPreferences,
    val healthGoals: List<String>, // atheletic, grow height, hair care, face care, weight loss,etc
    val healthIssues: List<String>,
    val healthMetrics: List<HealthMetric>
)
