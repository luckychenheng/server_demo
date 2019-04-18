package org.menacheri.jetclient.handlers.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler.Sharable;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;
import org.menacheri.jetclient.communication.NettyMessageBuffer;
import org.menacheri.jetclient.event.Events;

/**
 * This decoder will convert a Netty {@link ChannelBuffer} to a
 * {@link NettyMessageBuffer}. It will also convert
 * {@link Events#NETWORK_MESSAGE} events to {@link Events#SESSION_MESSAGE}
 * event.
 * 
 * @author Abraham Menacherry
 * 
 */
@Sharable
public class MessageBufferEventDecoder extends OneToOneDecoder
{
	
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception
	{
		if (null == msg)
		{
			return msg;
		}
		ChannelBuffer buffer = (ChannelBuffer) msg;
		byte opcode = buffer.readByte();
		if (opcode == Events.NETWORK_MESSAGE)
		{
			opcode = Events.SESSION_MESSAGE;
		}
		return Events.event(new NettyMessageBuffer(buffer), opcode);
	}
	
}
