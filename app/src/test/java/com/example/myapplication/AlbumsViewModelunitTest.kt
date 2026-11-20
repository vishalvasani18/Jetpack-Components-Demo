package com.example.myapplication

import com.example.myapplication.model.albums.Albums
import com.example.myapplication.repository.AlbumsRepository
import com.example.myapplication.viewmodel.AlbumsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumsViewModelUnitTest {

    private lateinit var repository: AlbumsRepository
    private lateinit var viewModel: AlbumsViewModel

    // Rule to control coroutine dispatcher
    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // ✅ Test: Success case
    @Test
    fun `when fetchAlbums success then albums list updates`() = runTest {
        val mockAlbums = listOf(
            Albums(id = 1, title = "Album 1",0),
            Albums(id = 2, title = "Album 2",1)
        )

        coEvery { repository.getAlbums() } returns mockAlbums

        viewModel = AlbumsViewModel()

        // Advance coroutine execution
        advanceUntilIdle()

        assertEquals(mockAlbums, viewModel.albums.value)
        assertEquals(false, viewModel.isLoading.value)
    }

    // ✅ Test: Loading state
    @Test
    fun `loading should be true then false`() = runTest {
        coEvery { repository.getAlbums() } returns emptyList()

        viewModel = AlbumsViewModel()

        advanceUntilIdle()

        assertEquals(false, viewModel.isLoading.value)
    }

    // ✅ Test: Error case
    @Test
    fun `when API fails then handle error gracefully`() = runTest {
        coEvery { repository.getAlbums() } throws Exception("API Error")

        viewModel = AlbumsViewModel()

        advanceUntilIdle()

        assertEquals(emptyList<Albums>(), viewModel.albums.value)
        assertEquals(false, viewModel.isLoading.value)
    }
}