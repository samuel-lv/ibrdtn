/*
 * DatagramService.h
 *
 *  Created on: 09.11.2012
 *      Author: morgenro
 */

#ifndef DATAGRAMSERVICE_H_
#define DATAGRAMSERVICE_H_

#include "core/Node.h"
#include <ibrcommon/net/vinterface.h>
#include <string>

namespace dtn
{
	namespace net
	{
		class DatagramException : public ibrcommon::Exception
		{
		public:
			DatagramException(const std::string &what) : ibrcommon::Exception(what)
			{};

			virtual ~DatagramException() throw() {};
		};

		class DatagramService
		{
		public:
			enum FLOWCONTROL
			{
				FLOW_NONE = 0,
				FLOW_STOPNWAIT = 1
			};

			enum HEADER_FLAGS
			{
				SEGMENT_FIRST = 0x02,
				SEGMENT_LAST = 0x01,
				SEGMENT_MIDDLE = 0x00
			};

			class Parameter
			{
			public:
				FLOWCONTROL flowcontrol;
				unsigned int max_seq_numbers;
				size_t max_msg_length;
				size_t initial_timeout;
				bool seq_check;
			};

			virtual ~DatagramService() = 0;

			/**
			 * Bind to the local socket.
			 * @throw If the bind fails, an DatagramException is thrown.
			 */
			virtual void bind() throw (DatagramException) = 0;

			/**
			 * Shutdown the socket. Unblock all calls on the socket (recv, send, etc.)
			 */
			virtual void shutdown() = 0;

			/**
			 * Send the payload as datagram to a defined destination
			 * @param address The destination address encoded as string.
			 * @param buf The buffer to send.
			 * @param length The number of available bytes in the buffer.
			 * @throw If the transmission wasn't successful this method will throw an exception.
			 */
			virtual void send(const char &type, const char &flags, const unsigned int &seqno, const std::string &address, const char *buf, size_t length) throw (DatagramException) = 0;

			/**
			 * Send the payload as datagram to all neighbors (broadcast)
			 * @param buf The buffer to send.
			 * @param length The number of available bytes in the buffer.
			 * @throw If the transmission wasn't successful this method will throw an exception.
			 */
			virtual void send(const char &type, const char &flags, const unsigned int &seqno, const char *buf, size_t length) throw (DatagramException) = 0;

			/**
			 * Receive an incoming datagram.
			 * @param buf A buffer to catch the incoming data.
			 * @param length The length of the buffer.
			 * @param address A buffer for the address of the sender.
			 * @throw If the receive call failed for any reason, an DatagramException is thrown.
			 * @return The number of received bytes.
			 */
			virtual size_t recvfrom(char *buf, size_t length, char &type, char &flags, unsigned int &seqno, std::string &address) throw (DatagramException) = 0;

			/**
			 * Get the tag for this service used in discovery messages.
			 * @return The tag as string.
			 */
			virtual const std::string getServiceTag() const = 0;

			/**
			 * Get the service description for this convergence layer. This
			 * data is used to contact this node.
			 * @return The service description as string.
			 */
			virtual const std::string getServiceDescription() const = 0;

			/**
			 * The used interface as vinterface object.
			 * @return A vinterface object.
			 */
			virtual const ibrcommon::vinterface& getInterface() const = 0;

			/**
			 * The protocol identifier for this type of service.
			 * @return
			 */
			virtual dtn::core::Node::Protocol getProtocol() const = 0;

			/**
			 * Returns the parameter for the connection.
			 * @return
			 */
			virtual const Parameter& getParameter() const = 0;
		};
	}
}

#endif /* DATAGRAMSERVICE_H_ */
