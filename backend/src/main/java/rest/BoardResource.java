package rest;

import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import service.BoardService;
import service.CardService;
import service.SectionService;
import service.TagService;
import entity.Board;
import entity.Card;
import entity.Section;
import entity.Tag;

@Path("/api")
@Produces({ "application/json", "application/xml" })
public class BoardResource {

	BoardService boardService = new BoardService();
	SectionService sectionService = new SectionService();
	CardService cardService = new CardService();
	TagService tagService = new TagService();

	/*********************** BOARD ***************************/

	@GET
	@Path("/boards")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<Board> getBoards() {
		return boardService.getAll();
	}

	@GET
	@Path("/boards/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board getBoardById(@PathParam("id") long id) {
		return boardService.getById(id);
	}

	@POST
	@Path("/boards")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveBoard(Board board) {
		return Response.ok(boardService.save(board)).status(201).build();
	}

	@DELETE
	@Path("/boards/{id}")
	public Response deleteBoard(@PathParam("id") long id) {
		boardService.delete(id);
		return Response.status(204).build();
	}

	@PUT
	@Path("boards/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board updateBoard(@PathParam("id") long id, Board board) {
		Board currentBoard = boardService.getById(id);
		if (currentBoard != null) {
			String title = board.getTitle();
			if (title != null) {
				currentBoard.setTitle(title);
			}
			boardService.update(currentBoard);
			return currentBoard;
		} else {
			return null;
		}
	}

	/*********************** SECTION ***************************/

	@POST
	@Path("boards/{id}/sections")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSection(@PathParam("id") long boardId, Section section) {
		sectionService.save(section);
		return Response.ok(boardService.addSection(boardId, section)).status(201).build();
	}

	@PUT
	@Path("boards/sections/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Section updateSection(@PathParam("id") long sectionId, Section section) {
		Section currentSection = sectionService.getById(sectionId);
		if (currentSection != null) {
			String title = section.getTitle();
			if (title != null) {
				currentSection.setTitle(title);
			}
			sectionService.update(currentSection);
			return currentSection;
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/boards/sections/{id}")
	public Response deleteSection(@PathParam("id") long sectionId) {
		sectionService.delete(sectionId);
		return Response.status(204).build();
	}

	/*********************** CARD ***************************/

	@POST
	@Path("boards/sections/{id}/cards")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCardToSection(@PathParam("id") long sectionId, Card card) {
		cardService.save(card);
		return Response.ok(sectionService.addCard(sectionId, card)).status(201).build();
	}

	@PUT
	@Path("boards/sections/cards/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Card updateCard(@PathParam("id") long cardId, Card card) {
		Card currentCard = cardService.getById(cardId);
		if (currentCard != null) {
			String title = card.getTitle();
			if (title != null) {
				currentCard.setTitle(title);
			}
			Date deadline = card.getDeadline();
			if (deadline != null) {
				currentCard.setDeadline(deadline);
			}
			String description = card.getDescription();
			if (description != null) {
				currentCard.setDescription(description);
			}
			int estimatedTime = card.getEstimatedTime();
			if (estimatedTime != 0) {
				currentCard.setEstimatedTime(estimatedTime);
			}
			String location = card.getLocation();
			if (location != null) {
				currentCard.setLocation(location);
			}
			String url = card.getUrl();
			if (url != null) {
				currentCard.setUrl(url);
			}
			cardService.update(currentCard);
			return currentCard;
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/boards/sections/cards/{id}")
	public Response deleteCard(@PathParam("id") long cardId) {
		cardService.delete(cardId);
		return Response.status(204).build();
	}

	/*********************** TAGS ***************************/

	@POST
	@Path("/boards/sections/cards/{id}/tags")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTag(@PathParam("id") long cardId, Tag tag) {
		tagService.save(tag);
		return Response.ok(cardService.addTag(cardId, tag)).status(201).build();
	}

	@PUT
	@Path("boards/sections/cards/tags/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Tag updateTag(@PathParam("id") long tagId, Tag tag) {
		Tag currentTag = tagService.getById(tagId);
		if (currentTag != null) {
			String title = tag.getTitle();
			if (title != null) {
				currentTag.setTitle(title);
			}
			tagService.update(currentTag);
			return currentTag;
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/boards/sections/cards/tags/{id}")
	public Response deleteTag(@PathParam("id") long tagId) {
		tagService.delete(tagId);
		return Response.status(204).build();
	}
}
