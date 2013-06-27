package com.gnorsilva.dojo;

import org.junit.Before;
import org.junit.Test;

import static com.gnorsilva.dojo.Match.MatchScoreBoard;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MatchTest {

    private MatchScoreBoard matchScoreBoard;

    Match match;

    @Before
    public void setUp() throws Exception {
        matchScoreBoard = mock(MatchScoreBoard.class);
        match = new Match(matchScoreBoard);
    }

    @Test
    public void when_serving_player_changes_the_board_is_updated(){
        match.winsRally(Player.TWO);

        verify(matchScoreBoard).servingPlayer(eq(Player.TWO));
    }

    @Test
    public void both_players_start_with_zero_points() throws Exception {
        assertThat(match.playerOnePoints(), is(0));
        assertThat(match.playerTwoPoints(), is(0));
    }

    @Test
    public void player_1_serves_first() throws Exception {
        assertThat(match.playerServing(), is(Player.ONE));
    }

    @Test
    public void when_player_one_is_serving_and_wins_a_rally_their_points_increase_by_one() throws Exception {
        assertThat(match.playerServing(), is(Player.ONE));

        match.winsRally(Player.ONE);

        assertThat(match.playerOnePoints(), is(1));
    }

    @Test
    public void when_player_two_is_serving_and_wins_a_rally_their_points_increase_by_one() throws Exception {
        int initialPlayerTwoPoints = match.playerTwoPoints();
        //given player two wins a rally and will serve next
        match.winsRally(Player.TWO);
        assertThat(match.playerServing(), is(Player.TWO));

        match.winsRally(Player.TWO);

        assertThat(match.playerTwoPoints(), is(initialPlayerTwoPoints + 1));
    }

    @Test
    public void when_player_one_is_serving_and_player_two_wins_rally_player_two_should_be_serving() {
        match.winsRally(Player.TWO);
        assertThat(match.playerServing(), is(Player.TWO));
    }

    @Test
    public void when_player_one_is_serving_and_player_two_wins_rally_player_two_score_does_not_increase() {
        assertThat(match.playerServing(), is(Player.ONE));

        match.winsRally(Player.TWO);

        assertThat(match.playerTwoPoints(), is(0));
    }

    @Test
    public void when_player_two_is_serving_and_player_one_wins_rally_player_one_score_does_not_increase() {
        int initialPlayerOneScore = match.playerOnePoints();

        //given player two wins rally and is next to serve
        match.winsRally(Player.TWO);
        assertThat(match.playerServing(), is(Player.TWO));

        //when
        match.winsRally(Player.ONE);

        assertThat(match.playerOnePoints(), is(initialPlayerOneScore));
    }


}
