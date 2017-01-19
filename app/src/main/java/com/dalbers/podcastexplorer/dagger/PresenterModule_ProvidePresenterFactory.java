// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.dalbers.podcastexplorer.dagger;

import com.dalbers.podcastexplorer.contracts.SearchContract;
import com.dalbers.podcastexplorer.data.PodcastMediaPlayer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class PresenterModule_ProvidePresenterFactory
    implements Factory<SearchContract.Presenter> {
  private final PresenterModule module;

  private final Provider<PodcastMediaPlayer> mediaPlayerProvider;

  private final Provider<SearchContract.Model> modelProvider;

  public PresenterModule_ProvidePresenterFactory(
      PresenterModule module,
      Provider<PodcastMediaPlayer> mediaPlayerProvider,
      Provider<SearchContract.Model> modelProvider) {
    assert module != null;
    this.module = module;
    assert mediaPlayerProvider != null;
    this.mediaPlayerProvider = mediaPlayerProvider;
    assert modelProvider != null;
    this.modelProvider = modelProvider;
  }

  @Override
  public SearchContract.Presenter get() {
    return Preconditions.checkNotNull(
        module.providePresenter(mediaPlayerProvider.get(), modelProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<SearchContract.Presenter> create(
      PresenterModule module,
      Provider<PodcastMediaPlayer> mediaPlayerProvider,
      Provider<SearchContract.Model> modelProvider) {
    return new PresenterModule_ProvidePresenterFactory(module, mediaPlayerProvider, modelProvider);
  }

  /** Proxies {@link PresenterModule#providePresenter(PodcastMediaPlayer, SearchContract.Model)}. */
  public static SearchContract.Presenter proxyProvidePresenter(
      PresenterModule instance, PodcastMediaPlayer mediaPlayer, SearchContract.Model model) {
    return instance.providePresenter(mediaPlayer, model);
  }
}