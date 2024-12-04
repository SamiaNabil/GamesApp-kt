
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.example.GameResponse
import com.example.gameslist.data.network.RetrofitInstance

class MainViewModel : ViewModel() {
    private val _games = MutableStateFlow<List<GameResponse>>(emptyList())
    val games: StateFlow<List<GameResponse>> get() = _games

    fun getGames() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiService.getGames()
                _games.value = response // Ensure 'response' is a List<GameResponse>
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }
}
